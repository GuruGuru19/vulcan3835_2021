package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3835.robot.subsystems.DriveSubsystem;


public class MoveBackCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private double time;

    public MoveBackCommand(DriveSubsystem driveSubsystem, double time) {
        this.driveSubsystem = driveSubsystem;
        this.time=time;
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(this.driveSubsystem);

    }

    @Override
    public void initialize() {
        driveSubsystem.power(-0.4, -0.4);
        withTimeout(this.time);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.power(0,0);
    }
}
