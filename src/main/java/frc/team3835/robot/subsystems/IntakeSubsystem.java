package frc.team3835.robot.subsystems;


import com.revrobotics.CANDigitalInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;

public class IntakeSubsystem implements Subsystem {

    private final static IntakeSubsystem INSTANCE = new IntakeSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static IntakeSubsystem getInstance() {
        return INSTANCE;
    }

    private PWMVictorSPX angleMotor;
    private PWMVictorSPX powerMotor;

    private DigitalInput limitSwitchDown;
    private DigitalInput limitSwitchUp;

    private IntakeSubsystem() {
        angleMotor = new PWMVictorSPX(Constants.intakeAngleMotor);
        powerMotor = new PWMVictorSPX(Constants.intakePowerMotor);

        limitSwitchDown = new DigitalInput(Constants.intakeLimitSwitchDown);
        limitSwitchUp = new DigitalInput(Constants.intakeLimitSwitchUp);
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
    }


    public void setAngleMotor(double power){
        if (limitSwitchDown.get() && power>0) {
            angleMotor.set(power*0.7);//בעלייה בכוח יותר גדול
        }
        else if (limitSwitchUp.get() && power<0){
            angleMotor.set(power*0.4);//בירידה הכוח יותר קטן
        }
    }

    public void setPowerMotor(){
        powerMotor.set(1);
    }
}

