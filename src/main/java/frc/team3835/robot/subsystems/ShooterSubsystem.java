package frc.team3835.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;

public class ShooterSubsystem implements Subsystem {

    private final static ShooterSubsystem INSTANCE = new ShooterSubsystem();
    @SuppressWarnings("WeakerAccess")
    public static ShooterSubsystem getInstance() {
        return INSTANCE;
    }

    private VictorSPX angleMotor;
    private CANSparkMax exitVelocityMotor;

    private AHRS gyro;

    private DigitalInput standingSwitch;
    private DigitalInput downSwitch;

    private double targetAngle;
    private double targetVelocity;

    private ShooterSubsystem() {
        angleMotor = new VictorSPX(Constants.shooterAngleMotor);
        exitVelocityMotor = new CANSparkMax(Constants.shooterExitVelocityMotor, CANSparkMaxLowLevel.MotorType.kBrushless);

        gyro = new AHRS(I2C.Port.kOnboard);//TODO: check port

        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
    }

    public AHRS getGyro(){
        return this.gyro;
    }

    public double getShooterAngle(){
        return 90-this.gyro.getAngle();
    }

    public void setTargetAngle(double angle){
        this.targetAngle = angle;
    }

    public void setVelocityTarget(double velocity){
        this.targetVelocity = velocity;
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

        //TODO: setup pid system to control exit velocity
    }
}

