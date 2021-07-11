package frc.team3835.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.IntakeSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;

import java.util.Set;

public class TakeBallsCommand extends CommandBase {
    private final IntakeSubsystem intake;
    private final ShooterSubsystem shooter;

    public TakeBallsCommand(IntakeSubsystem intake, ShooterSubsystem shooter) {
        addRequirements(intake);
        this.intake = intake;
        this.shooter = shooter;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (UI.getXboxAorBump()){
            intake.setTarget(false);
            intake.setOn(Constants.INTAKE_POWER_TAKE);
        }
        else if(UI.getXboxController().getBButton()){
            intake.setTarget(false);
            intake.setOn(-Constants.INTAKE_POWER_TAKE);
        }
        else {
            if (Constants.INTAKE_LIMIT_WHEN_SHOOTER_ANGLE_IS_LOW<shooter.getTargetAngle()){
                intake.setTarget(true);
            }
            else {
                intake.setTarget(false);
            }
            intake.setOn(0);
        }
    }

    @Override
    public boolean isFinished() {

        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intake.setOn(0);
    }
}
