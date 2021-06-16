package frc.team3835.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;

public class ShooterSubsystem implements Subsystem {

    private final static ShooterSubsystem INSTANCE = new ShooterSubsystem();
    @SuppressWarnings("WeakerAccess")
    public static ShooterSubsystem getInstance() {
        return INSTANCE;
    }

    private final VictorSPX angleMotor;
    private final CANSparkMax exitVelocityMotor;

    private final AHRS gyro;

    private final DigitalInput standingSwitch;
    private final DigitalInput downSwitch;

    private double targetAngle;
    private double targetVelocity;

    private final PIDController velocityPID;

    private ShooterSubsystem() {
        angleMotor = new VictorSPX(Constants.shooterAngleMotor);
        exitVelocityMotor = new CANSparkMax(Constants.shooterExitVelocityMotor, CANSparkMaxLowLevel.MotorType.kBrushless);

        gyro = new AHRS(I2C.Port.kOnboard);//TODO: check port

        standingSwitch = new DigitalInput(Constants.shooterUpSwitch);
        downSwitch = new DigitalInput(Constants.shooterDownSwitch);

        targetAngle = 0;
        targetVelocity = 0;

        velocityPID = new PIDController(Constants.SHOOTER_VELOCITY_KP, Constants.SHOOTER_VELOCITY_KI, Constants.SHOOTER_VELOCITY_KD);
        velocityPID.setSetpoint(0);
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
    }

    public AHRS getGyro(){
        return this.gyro;
    }

    public double getShooterAngle(){
        return 90-this.gyro.getRoll();
    }

    public void setTargetAngle(double angle){
        this.targetAngle = angle;
    }

    public double getTargetAngle(){
        return this.targetAngle;
    }

    public void setAngleMotor(double power){
        if (standingSwitch.get()){
            angleMotor.set(ControlMode.PercentOutput, power);
        }
    }
    public boolean getStandingSwitchIsPressed(){
        return !standingSwitch.get();
    }

    public void setVelocityTarget(double velocity){
        this.targetVelocity = velocity;
        this.velocityPID.reset();
        this.velocityPID.setSetpoint(velocity*Constants.SHOOTER_VELOCITY_CONVERTER_CONSTANT*Constants.SHOOTER_VELOCITY_CONSTANT*Constants.SHOOTER_VELOCITY_WHEEL_REDUCTION);//ממיר את המהירות הקווית למהירות זוויתית
    }

    public double getExitVelocity(){
        return exitVelocityMotor.getEncoder().getVelocity()*(1/Constants.SHOOTER_VELOCITY_CONVERTER_CONSTANT*Constants.SHOOTER_VELOCITY_CONSTANT*Constants.SHOOTER_VELOCITY_WHEEL_REDUCTION);
    }

    @Override
    public void periodic() {
        if(Math.abs(targetAngle - getShooterAngle())<Constants.SHOOTER_ANGLE_DEAD_ZONE || !(targetAngle>0&&targetAngle<=90)){
            angleMotor.set(ControlMode.PercentOutput, 0);
        }
        else if(downSwitch.get() && targetAngle<getShooterAngle()){
            angleMotor.set(ControlMode.PercentOutput, -Constants.SHOOTER_ANGLE_VELOCITY);
        }
        else if(standingSwitch.get()&&targetAngle>getShooterAngle()){
            angleMotor.set(ControlMode.PercentOutput, Constants.SHOOTER_ANGLE_VELOCITY);
        }
        else{
            angleMotor.set(ControlMode.PercentOutput, 0);
        }

        if (targetVelocity == 0){
            exitVelocityMotor.set(0);
        }
        else{
            exitVelocityMotor.set(-velocityPID.calculate(getExitVelocity()));
        }
    }
}

