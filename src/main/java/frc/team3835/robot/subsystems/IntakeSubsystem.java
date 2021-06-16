package frc.team3835.robot.subsystems;


import com.revrobotics.CANDigitalInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.team3835.robot.commands.TakeBallsCommand;

public class IntakeSubsystem implements Subsystem {

    private final static IntakeSubsystem INSTANCE = new IntakeSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static IntakeSubsystem getInstance() {
        return INSTANCE;
    }

    private boolean up;
    private double on;

    private VictorSPX angleMotor;
    private VictorSPX powerMotor;

    private DigitalInput limitSwitchDown;
    private DigitalInput limitSwitchUp;

    private IntakeSubsystem() {
        angleMotor = new VictorSPX(Constants.intakeAngleMotor);
        powerMotor = new VictorSPX(Constants.intakePowerMotor);

        limitSwitchDown = new DigitalInput(Constants.intakeLimitSwitchDown);
        limitSwitchUp = new DigitalInput(Constants.intakeLimitSwitchUp);

        setDefaultCommand(new TakeBallsCommand());

        up = true;
        on = 0;
    }

    public void setTarget(boolean up){
        this.up = up;
    }

    public boolean getTarget(){
        return this.up;
    }

    public void setOn(double power){
        on = power;
    }


    @Override
    public void periodic() {

        if (limitSwitchUp.get() && up) {
            angleMotor.set(ControlMode.PercentOutput, Constants.INTAKE_POWERUP);//בעלייה בכוח יותר גדול
        }
        else if (limitSwitchDown.get() && !up){
            angleMotor.set(ControlMode.PercentOutput, -Constants.INTAKE_POWERDOWN);//בירידה הכוח יותר קטן
        }
        else {
            angleMotor.set(ControlMode.PercentOutput,0);
        }

        powerMotor.set(ControlMode.PercentOutput, on);
    }
}

