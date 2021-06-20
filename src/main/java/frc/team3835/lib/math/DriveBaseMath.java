package frc.team3835.lib.math;

public class DriveBaseMath {
    public static class TankDriveMath{
        public static double leftPower(double forwardInput, double turningInput){
            return (turningInput/4)+forwardInput*(1-Math.abs(turningInput/4));
        }

        public static double rightPower(double forwardInput, double turningInput){
            return (-turningInput/4)+forwardInput*(1-Math.abs(turningInput/4));
        }
    }
}
