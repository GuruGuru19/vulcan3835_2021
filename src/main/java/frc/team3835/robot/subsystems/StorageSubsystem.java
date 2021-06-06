package frc.team3835.robot.subsystems;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3835.robot.Constants;

public class StorageSubsystem implements Subsystem {

    private final static StorageSubsystem INSTANCE = new StorageSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static StorageSubsystem getInstance() {
        return INSTANCE;
    }


    private PWMVictorSPX storageMotor;

    private AnalogInput storageSensor;

    private StorageSubsystem() {
        storageMotor = new PWMVictorSPX(Constants.storageMotor);
        storageSensor = new AnalogInput(Constants.storageSensor);
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
    }

    public void setPower(){
        storageMotor.set(0.8);
    }

    //TO DO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! (SENSOR)
    //public double getSensor(){
    //    return storageSensor.
    //}
}

