package frc.team3835.robot;

public class MathAssistant {
    public static double degToRad(double deg){
        return deg*Math.PI/180;
    }
    public static double radToDeg(double deg){
        return deg*180/Math.PI;
    }
    public static double cos(double value){
        return Math.cos(degToRad(value));
    }

    public static double sin(double value){
        return Math.sin(degToRad(value));
    }
    public static class ShootingAssistant{


        public static double getShootingAngle(double distance){
            double x1; double x2 = distance; double x3 = distance + 0.743;
            double y1 = Constants.SHOOTER_HIGHT; double y2 = 2.49555; double y3 = 2.49555;
            double A; double B;// double C;
            double a = 0; double a2;
            double ratio = 0, lastRatio = 0;
            double step = 1;
            while (Math.abs(ratio-1)>0.0001 && a < 90){
                if ((ratio<1&&lastRatio>1)||(ratio>1&&lastRatio<1)){
                    step*=0.1;
                }
                if (ratio<1){
                    a+=step;
                }
                else{
                    a-=step;
                }
                x1 = Constants.SHOOTER_LENGTH*cos(a);
                y1 = Constants.SHOOTER_LENGTH*sin(a)+Constants.SHOOTER_HIGHT;
                A = (((y3-y2)/(x3-x2))-((y2-y1)/(x2-x1)))/(x3-x1);
                B = ((y2-y1)/(x2-x1))-2*A*((x2+x1)/2);
                //C = y1-A*x1*x1-B*x1;
                a2 = radToDeg(Math.atan(2*A*x1+B));
                lastRatio = ratio;
                ratio = a/a2;
            }
            return a;
        }
        public static double getShootingVelocity(double distance){
            double x1 = 0; double x2 = distance; double x3 = distance + 0.743;
            double y1 = Constants.SHOOTER_HIGHT; double y2 = 2.49555; double y3 = 2.49555;
            double A = 0; double B = 0;// double C = 0;
            double a = 0; double a2;
            double ratio = 0, lastRatio = 0;
            double step = 1;
            while (Math.abs(ratio-1)>0.0001 && a < 90){
                if ((ratio<1&&lastRatio>1)||(ratio>1&&lastRatio<1)){
                    step*=0.1;
                }
                if (ratio<1){
                    a+=step;
                }
                else{
                    a-=step;
                }
                x1 = Constants.SHOOTER_LENGTH*cos(a);
                y1 = Constants.SHOOTER_LENGTH*sin(a)+Constants.SHOOTER_HIGHT;
                A = (((y3-y2)/(x3-x2))-((y2-y1)/(x2-x1)))/(x3-x1);
                B = ((y2-y1)/(x2-x1))-2*A*((x2+x1)/2);
                //C = y1-A*x1*x1-B*x1;
                a2 = radToDeg(Math.atan(2*A*x1+B));
                lastRatio = ratio;
                ratio = a/a2;
            }
            return Math.sqrt((-9.81*(B+2*A*x1))/(A*sin(2*a)));
        }
    }
    public static double specialSqr(double input){
        if (input==0)return 0;
        return Math.pow(input,3)/Math.abs(input);
    }
}
