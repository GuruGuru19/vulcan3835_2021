package frc.team3835.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.IntakeSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;

import java.util.Set;

public class TakeBallsCommand implements Command {
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
    private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
    private final Set<Subsystem> subsystems;

    public TakeBallsCommand() {
        this.subsystems = Set.of(this.intake);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (UI.getXboxX()){
            intake.setTarget(false);
            intake.setPowerMotor(Constants.INTAKE_POWER_TAKE);
        }
        else if(UI.getInstance().getL3()){
            intake.setTarget(false);
            intake.setPowerMotor(-Constants.INTAKE_POWER_TAKE);
        }
        else {
            intake.setPowerMotor(0);
            if (Constants.INTAKE_LIMIT_WHEN_SHOOTER_ANGLE_IS_LOW<shooter.getTargetAngle()&&Constants.INTAKE_LIMIT_WHEN_SHOOTER_ANGLE_IS_LOW<shooter.getShooterAngle()){
                intake.setTarget(true);
            }
            else {
                intake.setTarget(false);
            }
        }
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPowerMotor(0);
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
