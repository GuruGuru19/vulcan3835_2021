package frc.team3835.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team3835.robot.subsystems.DriveSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;
import frc.team3835.robot.subsystems.StorageSubsystem;

public class Auto1CommandGroup extends SequentialCommandGroup {
    public Auto1CommandGroup(ShooterSubsystem shooterSubsystem, StorageSubsystem storageSubsystem, DriveSubsystem driveSubsystem) {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new FooCommand(), new BarCommand());
        super(new ShooterResetCommand(), new AutoShootCommand(shooterSubsystem, storageSubsystem, driveSubsystem), new MoveBackCommand(driveSubsystem,3));
    }
}
