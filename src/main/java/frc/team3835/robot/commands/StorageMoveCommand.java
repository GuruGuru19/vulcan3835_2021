package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.StorageSubsystem;

import java.util.Set;

public class StorageMoveCommand implements Command {
    private final StorageSubsystem storageSubsystem = StorageSubsystem.getInstance();
    private final Set<Subsystem> subsystems;

    public StorageMoveCommand() {
        this.subsystems = Set.of(this.storageSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (UI.getXboxController().getBumper(GenericHID.Hand.kRight)){

        }
        //else if (){
//
        //}
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
