package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3835.robot.Constants;
import frc.team3835.robot.MathAssistant;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.CameraSubsystem;
import frc.team3835.robot.subsystems.DriveSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;
import frc.team3835.robot.subsystems.StorageSubsystem;


public class AutoShootCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();
    private final StorageSubsystem storageSubsystem = StorageSubsystem.getInstance();
    private final DriveSubsystem driveSubsystem = DriveSubsystem.getInstance();
    private final CameraSubsystem cameraSubsystem = CameraSubsystem.getInstance();

    public AutoShootCommand() {
        addRequirements(this.shooterSubsystem, this.storageSubsystem, this.driveSubsystem);
        //TODO: set up x-axis control
    }



    @Override
    public void initialize() {
        double distance = Math.tan(MathAssistant.degToRad(cameraSubsystem.getTy()+Constants.CAMERA_ANGLE))/(2.49555-Constants.CAMERA_HIGHT);//Y axis
        shooterSubsystem.setVelocityTarget(MathAssistant.ShootingAssistant.getShootingVelocity(distance));
        shooterSubsystem.setTargetAngle(MathAssistant.ShootingAssistant.getShootingAngle(distance));


    }

    @Override
    public void execute() {

        if (shooterSubsystem.isOnVelocitySetpoint() && shooterSubsystem.isOnAngleSetpoint()){
            storageSubsystem.setPower(Constants.STORAGE_POWER);
            withTimeout(Constants.SHOOTER_STORAGE_MOVING_TIME);
        }
        else {
            storageSubsystem.setPower(0);
        }
    }

    @Override
    public boolean isFinished() {
        return UI.getXboxController().getBackButton();
    }

    @Override
    public void end(boolean interrupted) {
        storageSubsystem.setPower(0);
        shooterSubsystem.setVelocityTarget(0);
    }
}
