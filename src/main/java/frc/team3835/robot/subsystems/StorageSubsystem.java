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


    private VictorSPX storageMotor;

    private AnalogInput storageSensor;
    private Encoder storageEncoder;

    private StorageSubsystem() {
        storageMotor = new VictorSPX(Constants.storageMotor);
        storageSensor = new AnalogInput(Constants.storageSensor);
        storageEncoder = new Encoder(Constants.storageEncoder[0],Constants.storageEncoder[1]);
        setDefaultCommand(new StorageMoveCommand());
    }

    public void setPower(double power){
        storageMotor.set(ControlMode.PercentOutput, power);
    }

    //TODO: !!!!!!! setup sensor
    //public double getSensor(){
    //    return storageSensor.
    //}
}

