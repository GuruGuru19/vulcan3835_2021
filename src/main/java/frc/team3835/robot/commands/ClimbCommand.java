package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.ClimbSubsystem;

import java.util.Set;

public class ClimbCommand implements Command {
    private final ClimbSubsystem climbSubsystem = ClimbSubsystem.getInstance();
    private final Set<Subsystem> subsystems;

    public ClimbCommand() {
        this.subsystems = Set.of(this.climbSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (UI.getXboxController().getPOV(0) == 0){
            climbSubsystem.setPower(1);
        }
        else if (UI.getXboxController().getPOV(0) == 180){
            climbSubsystem.setPower(-1);
        }
        else{
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

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
