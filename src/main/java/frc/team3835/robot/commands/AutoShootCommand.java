package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3835.lib.logger.LoggerAdapter;
import frc.team3835.robot.Constants;
import frc.team3835.robot.MathAssistant;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.CameraSubsystem;
import frc.team3835.robot.subsystems.DriveSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;
import frc.team3835.robot.subsystems.StorageSubsystem;


public class AutoShootCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final StorageSubsystem storageSubsystem;
    private final DriveSubsystem driveSubsystem;
    private final CameraSubsystem cameraSubsystem = CameraSubsystem.getInstance();

    PIDController xAxisController;

    public AutoShootCommand(ShooterSubsystem shooterSubsystem, StorageSubsystem storageSubsystem, DriveSubsystem driveSubsystem) {
        addRequirements(shooterSubsystem, storageSubsystem, driveSubsystem);
        this.shooterSubsystem = shooterSubsystem;
        this.storageSubsystem = storageSubsystem;
        this.driveSubsystem =driveSubsystem;
        xAxisController = new PIDController(Constants.DRIVE_TURN_PID_KP, Constants.DRIVE_TURN_PID_KI, Constants.DRIVE_TURN_PID_KD);
        xAxisController.setTolerance(Constants.DRIVE_TURN_PID_P_TOLERANCE, Constants.DRIVE_TURN_PID_V_TOLERANCE);
        xAxisController.setSetpoint(0);
    }



    @Override
    public void initialize() {
        if (cameraSubsystem.getTa()==0){
            end(false);
        }
        driveSubsystem.power(0,0);
        double distance = Math.tan(MathAssistant.degToRad(cameraSubsystem.getTy()+Constants.CAMERA_ANGLE))/(2.49555-Constants.CAMERA_HIGHT);//Y axis
        double velocity = MathAssistant.ShootingAssistant.getShootingVelocity(distance);
        double angle = MathAssistant.ShootingAssistant.getShootingAngle(distance);
        shooterSubsystem.setVelocityTarget(MathAssistant.ShootingAssistant.getShootingVelocity(distance));
        shooterSubsystem.setTargetAngle(MathAssistant.ShootingAssistant.getShootingAngle(distance));
        LoggerAdapter.log(this.getClass().getName()+" initialized. distance: "+distance+"[m], shooter angle: "+angle+"[deg], exit velocity: "+velocity+"[m/s], x-axis error: "+cameraSubsystem.getTxFromMid()+"[deg]");
    }

    @Override
    public void execute() {
        driveSubsystem.power(xAxisController.calculate(cameraSubsystem.getTxFromMid()), -xAxisController.calculate(cameraSubsystem.getTxFromMid()));

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
        if (interrupted){
            LoggerAdapter.log(this.getClass().getName()+" ended, by running out of time");
        }
        else {
            LoggerAdapter.log(this.getClass().getName()+" ended, by pressing the back button OR not finding a target");
        }
        storageSubsystem.setPower(0);
        shooterSubsystem.setVelocityTarget(0);
    }
}
