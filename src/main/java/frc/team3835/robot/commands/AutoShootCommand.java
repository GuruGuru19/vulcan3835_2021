package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        this.driveSubsystem = driveSubsystem;
    }

    public static double offSet(double d){
        return 0.4167*d*d*d-2*d*d-0.9167*d-1.5;
    }

    @Override
    public void initialize() {
        shoot = false;
        System.out.println("Auto Shooting");
        if (cameraSubsystem.getTa()==0){
            end(false);
            return;
        }
        driveSubsystem.power(0,0);
        double distance = SmartDashboard.getNumber("Camera/distance", 0)+Constants.CAMERA_DISTANCE_FROM_SHOOTER_X;//Y axis
        double velocity = MathAssistant.ShootingAssistant.getShootingVelocity(distance);
        double targetAngle = MathAssistant.ShootingAssistant.getShootingAngle(distance);
        shooterSubsystem.setVelocityTarget(velocity);
        shooterSubsystem.setTargetAngle(targetAngle+offSet(distance));
        SmartDashboard.putNumber("AutoShoot/ angle off set", offSet(distance));
        LoggerAdapter.log(this.getClass().getName()+" initialized. distance: "+distance+"[m], shooter angle: "+targetAngle+"[deg], exit velocity: "+velocity+"[m/s], x-axis error: "+cameraSubsystem.getTxFromMid()+"[deg]");
    }

    boolean shoot;

    @Override
    public void execute() {
        //driveSubsystem.power(xAxisController.calculate(cameraSubsystem.getTxFromMid()), -xAxisController.calculate(cameraSubsystem.getTxFromMid()));
        boolean xAxisOnTarget = Math.abs(cameraSubsystem.getTxFromMid())<Constants.DRIVE_TURN_ANGLE_TOLERANCE/2;
        SmartDashboard.putBoolean("AutoShoot/x on target", xAxisOnTarget);

        if (xAxisOnTarget){
            driveSubsystem.power(0,0);
        }
        else if (cameraSubsystem.getTxFromMid()<0){
            driveSubsystem.power(0,Constants.DRIVE_SLOW_TURN_POWER);
        }
        else if (cameraSubsystem.getTxFromMid()>0){
            driveSubsystem.power(Constants.DRIVE_SLOW_TURN_POWER,0);
        }
        else {
            driveSubsystem.power(0,0);
        }
        SmartDashboard.putBoolean("AutoShoot/ moving storage", shooterSubsystem.isOnVelocitySetpoint() && shooterSubsystem.isOnAngleSetpoint()&&xAxisOnTarget);
        if ((shooterSubsystem.isOnVelocitySetpoint() && shooterSubsystem.isOnAngleSetpoint()&&xAxisOnTarget)){
            storageSubsystem.setPower(Constants.STORAGE_POWER);
            //withTimeout(Constants.SHOOTER_STORAGE_MOVING_TIME);
        }
        else {
            storageSubsystem.setPower(0);
        }
    }

    @Override
    public boolean isFinished() {
        return UI.getXbox2Controller().getBackButton() || UI.getXboxController().getBackButton();
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
