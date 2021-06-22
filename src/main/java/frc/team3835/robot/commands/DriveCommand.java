package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3835.lib.math.DriveBaseMath;
import frc.team3835.robot.MathAssistant;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.DriveSubsystem;


public class DriveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;

    private boolean hapticFeedback;

    private double p;
    private double r;

    public DriveCommand(boolean hapticFeedback, DriveSubsystem driveSubsystem) {
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(driveSubsystem);
        this.driveSubsystem=driveSubsystem;
        this.hapticFeedback=hapticFeedback;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        r = UI.getXboxController().getTriggerAxis(GenericHID.Hand.kRight)-UI.getXboxController().getTriggerAxis(GenericHID.Hand.kLeft);
        p = UI.getLeftJoystickY();

        //r = MathAssistant.specialSqr(r);
        //p = MathAssistant.specialSqr(p);

        driveSubsystem.power(DriveBaseMath.TankDriveMath.leftPower(p, r), DriveBaseMath.TankDriveMath.rightPower(p, r));

        if (hapticFeedback){
            UI.getXboxController().setRumble(GenericHID.RumbleType.kLeftRumble,driveSubsystem.getGyro().getAccelFullScaleRangeG());
        }

        //System.out.println("r: "+r+", p: "+p);
    }

    @Override
    public boolean isFinished() {
        if (hapticFeedback){
            UI.getXboxController().setRumble(GenericHID.RumbleType.kLeftRumble,0);
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.power(0,0);
    }
}
