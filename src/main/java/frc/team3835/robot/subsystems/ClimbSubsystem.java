package frc.team3835.robot.subsystems;


import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;

public class ClimbSubsystem implements Subsystem {

    private final static ClimbSubsystem INSTANCE = new ClimbSubsystem();
    @SuppressWarnings("WeakerAccess")
    public static ClimbSubsystem getInstance() { return INSTANCE; }


    private CANSparkMax climbMotor;
    private DigitalInput downSwitch;


    private ClimbSubsystem() {
        this.climbMotor = new CANSparkMax(Constants.climbMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
        downSwitch = new DigitalInput(Constants.climbSwitch);
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
        if (power<0 && downSwitch.get()){
            climbMotor.set(power);
        }
        else if (power>0){
            climbMotor.set(power);
        }
        climbMotor.set(0);
    }
}

