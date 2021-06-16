package frc.team3835.robot.subsystems;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.team3835.robot.commands.StorageMoveCommand;

public class StorageSubsystem implements Subsystem {

    private final static StorageSubsystem INSTANCE = new StorageSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static StorageSubsystem getInstance() { return INSTANCE; }

    private VictorSPX storageMotorUp;
    private VictorSPX storageMotorDown;

    private AnalogInput storageSensor;

    private StorageSubsystem() {
        storageMotorUp = new VictorSPX(Constants.storageMotorUp);
        storageMotorDown = new VictorSPX(Constants.storageMotorDown);

        storageSensor = new AnalogInput(Constants.storageSensor);
        setDefaultCommand(new StorageMoveCommand());
    }

    public void setPower(double power){
        storageMotorUp.set(ControlMode.PercentOutput, power);
        storageMotorDown.set(ControlMode.PercentOutput, power);
    }

    @Override
    public void periodic() {
        if (storageSensor.getVoltage() < 2.8 && storageSensor.getVoltage() > 2.2){
            setPower(Constants.STORAGE_POWER);
        }
        else{
            setPower(0);
        }
    }
}

