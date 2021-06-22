package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.ShooterSubsystem;


public class ShooterMoveCommand extends CommandBase {
    private ShooterSubsystem shooterSubsystem;

    private boolean on;


    public ShooterMoveCommand(ShooterSubsystem shooterSubsystem) {
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        on = false;
    }

    @Override
    public void execute() {
//        System.out.println(UI.getRightJoystickY());
//        System.out.println(shooterSubsystem==null);
        if (UI.getRightJoystickY() < 0 && !shooterSubsystem.getStandingSwitchIsPressed()){
            shooterSubsystem.setTargetAngle(shooterSubsystem.getTargetAngle()+0.5);
        }
        else if(UI.getRightJoystickY() > 0 && !shooterSubsystem.getDownSwitchIsPressed()){
            shooterSubsystem.setTargetAngle(shooterSubsystem.getTargetAngle()-0.5);
        }
        System.out.println(UI.getR3());

        if (UI.getXboxController().getStickButtonPressed(GenericHID.Hand.kRight)){
            if (on) {
                on = false;
                shooterSubsystem.setVelocityTarget(0);
            }
            else {
                on = true;
                shooterSubsystem.setVelocityTarget(20);
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

    }
}
