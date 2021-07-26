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
        setDefaultCommand(new StorageMoveCommand(this));
    }

    public void setPower(double power){
        storageMotorUp.set(ControlMode.PercentOutput, 1.25*power);
        storageMotorDown.set(ControlMode.PercentOutput, -power);
    }

    public double getVoltage(){
        return storageSensor.getVoltage();
    }

    @Override
    public void periodic() {
        //System.out.println("storage: "+storageSensor.getVoltage());
//        if (storageSensor.getVoltage() < Constants.STORAGE_SENSOR_BALL_UP_BOUND && storageSensor.getVoltage() > Constants.STORAGE_SENSOR_BALL_DOWN_BOUND){
//            setPower(Constants.STORAGE_POWER);
//        }
//        else{
//            setPower(0);
//        }
    }
}

