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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import frc.team3835.robot.MathAssistant;
import frc.team3835.robot.commands.ShooterMoveCommand;

public class ShooterSubsystem implements Subsystem {

    private final static ShooterSubsystem INSTANCE = new ShooterSubsystem();
    @SuppressWarnings("WeakerAccess")
    public static ShooterSubsystem getInstance() {
        return INSTANCE;
    }

    private final VictorSPX angleMotor;
    private final CANSparkMax exitVelocityMotor;

    private final AHRS gyro;
    private final Encoder encoder;

    private final DigitalInput standingSwitch;
    private final DigitalInput downSwitch;

    private double targetAngle;
    private double targetVelocity;

    private boolean inUse;
    private double lastAngle;

    private ShooterSubsystem() {
        angleMotor = new VictorSPX(Constants.shooterAngleMotor);
        exitVelocityMotor = new CANSparkMax(Constants.shooterExitVelocityMotor, CANSparkMaxLowLevel.MotorType.kBrushless);

        gyro = new AHRS(I2C.Port.kOnboard);//TODO: check port
        encoder = new Encoder(Constants.shooterEncoder[0], Constants.shooterEncoder[1]);

        standingSwitch = new DigitalInput(Constants.shooterUpSwitch);
        downSwitch = new DigitalInput(Constants.shooterDownSwitch);

        targetAngle = 90;
        targetVelocity = 0;
        inUse=false;

        lastAngle = -1;
        setDefaultCommand(new ShooterMoveCommand(this));

        SmartDashboard.putNumber("Shooter/target angle", 90);
    }

    public boolean getInUse(){
        return inUse;
    }

    public void setInUse(boolean inUse){
        this.inUse=inUse;
    }

    public AHRS getGyro(){
        return this.gyro;
    }

    public double getShooterAngle(){
        return 90-this.gyro.getRoll();
    }

    public void setTargetAngle(double angle){
        if (angle>90){
            SmartDashboard.putNumber("Shooter/target angle", 90);
            return;
        }
        if (angle<0){
            SmartDashboard.putNumber("Shooter/target angle", 90);
            return;
        }
        SmartDashboard.putNumber("Shooter/target angle", angle);
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
    public boolean getDownSwitchIsPressed(){
        return !downSwitch.get();
    }

    public void setVelocityTarget(double velocity){
        this.targetVelocity = velocity*Constants.SHOOTER_VELOCITY_CONVERTER_CONSTANT*Constants.SHOOTER_VELOCITY_CONSTANT*Constants.SHOOTER_VELOCITY_WHEEL_REDUCTION;
    }

    public double getExitVelocity(){
        return exitVelocityMotor.getEncoder().getVelocity()*(1/(Constants.SHOOTER_VELOCITY_CONVERTER_CONSTANT*Constants.SHOOTER_VELOCITY_CONSTANT*Constants.SHOOTER_VELOCITY_WHEEL_REDUCTION));
    }

    public boolean isOnVelocitySetpoint(){
        return Math.abs(exitVelocityMotor.getEncoder().getVelocity()-targetVelocity) < Constants.SHOOTER_VELOCITY_TOLERANCE;
    }

    public boolean isOnAngleSetpoint(){
        return Math.abs(targetAngle - getShooterAngle())<Constants.SHOOTER_ANGLE_DEAD_ZONE;
    }

    @Override
    public void periodic() {
        if(inUse){
            return;
        }
        targetAngle = SmartDashboard.getNumber("Shooter/target angle", 90);
        SmartDashboard.putNumber("Shooter/angle", getShooterAngle());
        SmartDashboard.putNumber("Shooter/encoder", encoder.get());
        //System.out.println("target angle: "+targetAngle+", angle: "+getShooterAngle());
        //System.out.println("angle target: "+targetAngle+", angle: "+getShooterAngle()+", gyro: "+gyro.getRoll()+", ss: "+standingSwitch.get()+", ds: "+downSwitch.get());
        double power = 0;
        if(Math.abs(targetAngle - getShooterAngle())<Constants.SHOOTER_ANGLE_DEAD_ZONE || !(targetAngle>=0&&targetAngle<=90)){
            power = 0;
        }
        else if(downSwitch.get() && targetAngle<getShooterAngle()){
            power = 0.8+(0.4-(lastAngle-getShooterAngle()))*0.2;
        }
        else if(standingSwitch.get()&&targetAngle>getShooterAngle()){
            power = -0.8+(-0.4-(lastAngle-getShooterAngle()))*0.2;
        }
        angleMotor.set(ControlMode.PercentOutput, power);
        SmartDashboard.putNumber("Shooter/power", power);

        if (targetVelocity==0){
            exitVelocityMotor.set(0);
        }
        else{
            exitVelocityMotor.setVoltage(-(12/Constants.SHOOTER_VELOCITY_AT_12V)*targetVelocity);
        }

        lastAngle = getShooterAngle();
    }
}

