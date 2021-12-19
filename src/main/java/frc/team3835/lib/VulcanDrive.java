package frc.team3835.lib;

import edu.wpi.first.wpilibj.SpeedController;

import java.util.List;

//GuruGuru19 18/12/2021
public class VulcanDrive {
    private final List<SpeedController> leftList;
    private final List<SpeedController> rightList;
    private double r0; // the rotational element while |p| = 0 |r| = 1
    private double r1; // the rotational element while |p| = 1 |r| = 1

    public VulcanDrive(List<SpeedController> leftSpeedControllerList, List<SpeedController> rightSpeedControllerList){
        this.leftList = leftSpeedControllerList;
        this.rightList = rightSpeedControllerList;
        this.r0 = 0.5;
        this.r1 = 0.5;
    }

    public VulcanDrive(List<SpeedController> leftSpeedControllerList, List<SpeedController> rightSpeedControllerList, double r0, double r1){
        this.leftList = leftSpeedControllerList;
        this.rightList = rightSpeedControllerList;
        this.r0 = r0;
        this.r1 = r1;
    }

    private static double Correct(double x){
        if (x >= 0){
            return Math.min(1,x);
        }
        return Math.max(-1,x);
    }

    public double leftElement(double p, double r){
        p = Correct(p);
        r = Correct(r);
        if (p>=0){
            return (Math.min(1,1+r*r1)-r*r0)*p+r*r0;
        }
        return -(Math.max(-1,-1+r*r1)-r*r0)*p+r*r0;
    }

    public double rightElement(double p, double r){
        p = Correct(p);
        r = Correct(r);
        if (p>=0){
            return (Math.min(1,1-r*r1)+r*r0)*p-r*r0;
        }
        return -(Math.max(-1,-1-r*r1)+r*r0)*p-r*r0;

    }

    public void controllerPowerDrive(double p, double r){
        for (SpeedController sc:
             this.leftList) {
            sc.set(leftElement(p,r));
        }
        for (SpeedController sc:
                this.rightList) {
            sc.set(rightElement(p,r));
        }
    }
}
