package frc.team3835.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class CameraSubsystem implements Subsystem {

    private final static CameraSubsystem INSTANCE = new CameraSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static CameraSubsystem getInstance() {
        return INSTANCE;
    }

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    NetworkTableEntry tv;

    private CameraSubsystem() {
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        tv = table.getEntry("tv");
    }

    public double getTx() {
        return tx.getDouble(0);
    }

    public double getTy() {
        return ty.getDouble(0);
    }

    public double getTa() {
        return ta.getDouble(0);
    }

    public double getTv() {
        return tv.getDouble(0);
    }

    public double getTxFromMid(){
        return 0;//TODO:setup
    }

    public Number getPipeline(){
        return table.getEntry("getpipe").getNumber(0);
    }

    public void setPipeline(int number){
        if(number>=0&&number<10){
            table.getEntry("pipeline").setNumber(number);
        }
    }

    public NetworkTableEntry getEntry(String entryName){
        return table.getEntry(entryName);
    }
}

