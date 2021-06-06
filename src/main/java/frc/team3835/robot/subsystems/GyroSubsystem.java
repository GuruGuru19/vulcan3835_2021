package frc.team3835.robot.subsystems;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class GyroSubsystem implements Subsystem {

    private final static GyroSubsystem INSTANCE = new GyroSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static GyroSubsystem getInstance() {
        return INSTANCE;
    }

    private AHRS gyro;

    private GyroSubsystem() {
        gyro = new AHRS(SerialPort.Port.kOnboard);
    }

    public AHRS getGyro() {
        return gyro;
    }

}

