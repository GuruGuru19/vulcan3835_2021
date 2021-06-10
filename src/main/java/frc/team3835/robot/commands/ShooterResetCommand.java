package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import frc.team3835.robot.subsystems.ShooterSubsystem;

import java.util.Set;

public class ShooterResetCommand implements Command {
    private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
    private final Set<Subsystem> subsystems;

    public ShooterResetCommand() {
        subsystems = Set.of(this.shooter);
        //
    }

    @Override
    public void initialize() {
        shooter.setAngleMotor(Constants.SHOOTER_ANGLE_VELOCITY);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        if (shooter.getStandingSwitchIsPressed()){
            shooter.setAngleMotor(0);
            shooter.getGyro().reset();
            return true;
        }
        return false;
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return subsystems;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setAngleMotor(0);
    }
}
