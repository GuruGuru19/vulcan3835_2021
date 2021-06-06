package frc.team3835.robot.subsystems;


import com.revrobotics.CANDigitalInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeSubsystem implements Subsystem {

    private final static IntakeSubsystem INSTANCE = new IntakeSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static IntakeSubsystem getInstance() {
        return INSTANCE;
    }

    private boolean UP;

    private VictorSPX angleMotor;
    private VictorSPX powerMotor;

    private DigitalInput limitSwitchDown;
    private DigitalInput limitSwitchUp;

    private static final double POWERUP = 0.7;
    private static final double POWERDOWN = 0.4;

    private IntakeSubsystem() {
        angleMotor = new VictorSPX(Constants.intakeAngleMotor);
        powerMotor = new VictorSPX(Constants.intakePowerMotor);

        limitSwitchDown = new DigitalInput(Constants.intakeLimitSwitchDown);
        limitSwitchUp = new DigitalInput(Constants.intakeLimitSwitchUp);
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
    }

    public void setTarget(boolean up){
        this.UP = up;
    }

    public boolean getTarget(){
        return this.UP;
    }

    public void setPowerMotor(double power){
        powerMotor.set(ControlMode.PercentOutput,power);
    }


    @Override
    public void periodic() {

        if (limitSwitchUp.get() && UP) {
            angleMotor.set(ControlMode.PercentOutput, POWERUP);//בעלייה בכוח יותר גדול
        }
        else if (limitSwitchDown.get() && !UP){
            angleMotor.set(ControlMode.PercentOutput, -POWERDOWN);//בירידה הכוח יותר קטן
        }
        else {
            angleMotor.set(ControlMode.PercentOutput,0);
        }
    }
}

