package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.ClimbSubsystem;


public class ClimbCommand extends CommandBase {
    private final ClimbSubsystem climbSubsystem;

    public ClimbCommand(ClimbSubsystem climbSubsystem) {
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(climbSubsystem);
        this.climbSubsystem=climbSubsystem;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        if (UI.getXboxController().getPOV(0)==0){
            climbSubsystem.setPower(1);
        }
        else if (UI.getXboxController().getPOV(0)==180){
            climbSubsystem.setPower(-1);
        }
        else {
            climbSubsystem.setPower(0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        climbSubsystem.setPower(0);
    }
}
