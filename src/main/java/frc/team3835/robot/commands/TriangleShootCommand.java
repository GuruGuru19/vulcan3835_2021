package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3835.lib.logger.LoggerAdapter;
import frc.team3835.robot.Constants;
import frc.team3835.robot.MathAssistant;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.DriveSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;
import frc.team3835.robot.subsystems.StorageSubsystem;


public class TriangleShootCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final StorageSubsystem storageSubsystem;
    private final DriveSubsystem driveSubsystem;

    public TriangleShootCommand(ShooterSubsystem shooterSubsystem, StorageSubsystem storageSubsystem, DriveSubsystem driveSubsystem) {
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(shooterSubsystem, storageSubsystem, driveSubsystem);
        this.shooterSubsystem = shooterSubsystem;
        this.storageSubsystem = storageSubsystem;
        this.driveSubsystem =driveSubsystem;
    }

    @Override
    public void initialize() {
        driveSubsystem.power(0,0);
        double velocity = Constants.SHOOTER_TRIANGLE_SHOT_VELOCITY;
        double targetAngle = Constants.SHOOTER_TRIANGLE_SHOT_ANGLE;
        shooterSubsystem.setVelocityTarget(velocity);
        shooterSubsystem.setTargetAngle(targetAngle);
        LoggerAdapter.log(this.getClass().getName()+" initialized. shooter angle: "+targetAngle+"[deg], exit velocity: "+velocity+"[m/s]");
    }

    @Override
    public void execute() {
        if (shooterSubsystem.isOnVelocitySetpoint() && shooterSubsystem.isOnAngleSetpoint()){
            storageSubsystem.setPower(Constants.STORAGE_POWER);
            //withTimeout(Constants.SHOOTER_STORAGE_MOVING_TIME);
        }
        else {
            storageSubsystem.setPower(0);
        }
    }

    @Override
    public boolean isFinished() {
        return UI.getXbox2Controller().getBackButton();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted){
            LoggerAdapter.log(this.getClass().getName()+" ended, by running out of time");
        }
        else {
            LoggerAdapter.log(this.getClass().getName()+" ended, by pressing the back button");
        }
        storageSubsystem.setPower(0);
        shooterSubsystem.setVelocityTarget(0);
    }
}
