package frc.team3835.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3835.robot.Constants;
import frc.team3835.robot.MathAssistant;

public class CameraSubsystem extends SubsystemBase {

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
        return 90-MathAssistant.radToDeg(Math.atan(
                (SmartDashboard.getNumber("Camera/distance",0)*MathAssistant.sin(90-tx.getDouble(0)))
                /(SmartDashboard.getNumber("Camera/distance",0)*MathAssistant.cos(90-tx.getDouble(0))-Constants.CAMERA_DISTANCE_FROM_MID)));
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

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Camera/tx",tx.getDouble(-999));
        SmartDashboard.putNumber("Camera/tx (from mid)",getTxFromMid());
        SmartDashboard.putNumber("Camera/ty",ty.getDouble(-999));
        SmartDashboard.putNumber("Camera/ta",ta.getDouble(-999));
        SmartDashboard.putNumber("Camera/distance", (2.49555-Constants.CAMERA_HIGHT)/Math.tan(MathAssistant.degToRad(ty.getDouble(0)+ Constants.CAMERA_ANGLE)));
    }
}

