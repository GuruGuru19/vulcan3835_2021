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
        //shooterSubsystem.setInUse(true);
    }

    @Override
    public void execute() {

//        if (UI.getXbox2Controller().getY(GenericHID.Hand.kRight) < 0 && !shooterSubsystem.getStandingSwitchIsPressed()){
//            shooterSubsystem.setInUse(true);
//            shooterSubsystem.setAngleMotor(UI.getRightJoystickY());
//            on = true;
//        }
//        else if(UI.getXbox2Controller().getY(GenericHID.Hand.kRight) > 0 && !shooterSubsystem.getDownSwitchIsPressed()){
//            shooterSubsystem.setInUse(true);
//            shooterSubsystem.setAngleMotor(UI.getXbox2Controller().getY(GenericHID.Hand.kRight));
//            on = true;
//            //shooterSubsystem.setTargetAngle(shooterSubsystem.getTargetAngle()-0.5);
//        }
//        else if (on){
//            on = false;
//            shooterSubsystem.setTargetAngle(shooterSubsystem.getShooterAngle());
//        }

       

        if (UI.getXbox2Controller().getStickButtonPressed(GenericHID.Hand.kRight)){
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
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
