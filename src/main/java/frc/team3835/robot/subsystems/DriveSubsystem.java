package frc.team3835.robot.subsystems;


import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import frc.team3835.robot.commands.DriveCommand;

public class DriveSubsystem implements Subsystem {
    private final static DriveSubsystem INSTANCE = new DriveSubsystem();
    @SuppressWarnings("WeakerAccess")
    public static DriveSubsystem getInstance() {
        return INSTANCE;
    }

    private CANSparkMax leftFront;
    private CANSparkMax leftBack;

    private CANSparkMax rightFront;
    private CANSparkMax rightBack;

    private AHRS gyro;

    private static final double gear = 1/5;
    private static final double wheelDiameter = 6 * 25.4;


    private DriveSubsystem() {
        this.leftFront = new CANSparkMax(Constants.driveSubLeftFront, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.leftBack = new CANSparkMax(Constants.driveSubLeftBack, CANSparkMaxLowLevel.MotorType.kBrushless);

        this.rightFront = new CANSparkMax(Constants.driveSubRightFront, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightBack = new CANSparkMax(Constants.driveSubRightBack, CANSparkMaxLowLevel.MotorType.kBrushless);

        gyro = new AHRS(SPI.Port.kMXP);

        setDefaultCommand(new DriveCommand(false, this));

        System.out.println("DriveSub ok");
    }

    public void power(double leftValue, double rightValue){
        this.leftFront.set(leftValue);
        this.leftBack.set(leftValue);
        this.rightFront.set(-rightValue);
        this.rightBack.set(-rightValue);
        //System.out.println("drive: ("+leftValue+", "+rightValue+")");
    }

    public CANEncoder getEncoder(boolean left){
        return left?leftFront.getEncoder():rightFront.getEncoder();
    }

    public double getEncoderValue(boolean left){
        return left?leftFront.getEncoder().getPosition():rightFront.getEncoder().getPosition();
    }

    public AHRS getGyro(){
        return this.gyro;
    }
}

