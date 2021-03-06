package frc.team3835.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import frc.team3835.robot.UI;
import frc.team3835.robot.subsystems.StorageSubsystem;

import java.util.Set;

public class StorageMoveCommand implements Command {
    private final StorageSubsystem storageSubsystem;
    private final Set<Subsystem> subsystems;

    public StorageMoveCommand(StorageSubsystem storageSubsystem) {
        this.subsystems = Set.of(storageSubsystem);
        this.storageSubsystem=storageSubsystem;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (UI.getXbox2Controller().getBumper(GenericHID.Hand.kRight)&&UI.getXbox2Controller().getBumper(GenericHID.Hand.kLeft)){
            storageSubsystem.setPower(0);
        }
        else if (UI.getXbox2Controller().getBumper(GenericHID.Hand.kLeft)){
            storageSubsystem.setPower(-Constants.STORAGE_POWER);
        }
        else if (storageSubsystem.getVoltage() < Constants.STORAGE_SENSOR_BALL_UP_BOUND && storageSubsystem.getVoltage() > Constants.STORAGE_SENSOR_BALL_DOWN_BOUND){
            storageSubsystem.setPower(Constants.STORAGE_POWER*0.8);
        }
        else if (UI.getXbox2Controller().getBumper(GenericHID.Hand.kRight)){
            storageSubsystem.setPower(Constants.STORAGE_POWER);
        }
        else {
            storageSubsystem.setPower(0);
        }
    }

    @Override
    public boolean isFinished() {

        return false;
    }

    @Override
    public void end(boolean interrupted) {
        storageSubsystem.setPower(0);
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
