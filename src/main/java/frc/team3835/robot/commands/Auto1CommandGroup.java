package frc.team3835.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team3835.robot.subsystems.DriveSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;
import frc.team3835.robot.subsystems.StorageSubsystem;

public class Auto1CommandGroup extends SequentialCommandGroup {
    public Auto1CommandGroup(ShooterSubsystem shooterSubsystem, StorageSubsystem storageSubsystem, DriveSubsystem driveSubsystem) {
        super(new ShooterResetCommand().withTimeout(3),
                new AutoShootCommand(shooterSubsystem, storageSubsystem, driveSubsystem).withTimeout(6),
                    new MoveBackCommand(driveSubsystem).withTimeout(1),
                        new ShooterResetCommand().withTimeout(3));
    }
}
