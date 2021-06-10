package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import frc.team3835.robot.subsystems.IntakeSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;

import java.util.Set;

public class TranchRunCommand implements Command {
    private final IntakeSubsystem intakeSubsystem = IntakeSubsystem.getInstance();
    private final ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();
    private final Set<Subsystem> subsystems;

    public TranchRunCommand() {
        this.subsystems = Set.of(this.intakeSubsystem, this.shooterSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.setTargetAngle(0);
        intakeSubsystem.setTarget(false);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
