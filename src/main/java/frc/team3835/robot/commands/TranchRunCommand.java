package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import frc.team3835.robot.subsystems.IntakeSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;

import java.util.Set;

public class TranchRunCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final ShooterSubsystem shooterSubsystem;

    public TranchRunCommand(IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem) {
        this.intakeSubsystem=intakeSubsystem;
        this.shooterSubsystem=shooterSubsystem;
        addRequirements(intakeSubsystem, shooterSubsystem);
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

}
