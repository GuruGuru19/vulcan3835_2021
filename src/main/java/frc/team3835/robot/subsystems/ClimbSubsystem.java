package frc.team3835.robot.subsystems;


import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;

public class ClimbSubsystem implements Subsystem {

    private final static ClimbSubsystem INSTANCE = new ClimbSubsystem();
    @SuppressWarnings("WeakerAccess")
    public static ClimbSubsystem getInstance() { return INSTANCE; }


    private CANSparkMax climbMotor;


    private ClimbSubsystem() {
        this.climbMotor = new CANSparkMax(Constants.climbMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
    }

    public CANEncoder getEncoder(){
        return climbMotor.getEncoder();
    }

    public double getEncoderValue(){
        return climbMotor.getEncoder().getPosition();
    }

    public void setPower(double power){
        climbMotor.set(power);
    }
}

