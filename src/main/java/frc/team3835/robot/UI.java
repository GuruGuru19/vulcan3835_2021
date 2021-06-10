package frc.team3835.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class UI {

    private static UI instance = new UI();

    public static final double JOY_DEADZONE = 0.08;

    public final Joystick LEFT_JOY = new Joystick(Constants.LeftJoystick);
    public final Joystick RIGHT_JOY = new Joystick(Constants.RightJoystick);
    public static final XboxController xboxController = new XboxController(Constants.Xbox);

    Button aButton = new JoystickButton(xboxController, Constants.aButton);
    Button bButton = new JoystickButton(xboxController, Constants.bButton);
    Button xButton = new JoystickButton(xboxController, Constants.xButton);
    Button yButton = new JoystickButton(xboxController, Constants.yButton);
    Button l1Button = new JoystickButton(xboxController, Constants.l1Button);
    Button l2Button = new JoystickButton(xboxController, Constants.l2Button);
    Button l3Button = new JoystickButton(xboxController, Constants.l3Button);
    Button r1Button = new JoystickButton(xboxController, Constants.r1Button);
    Button r2Button = new JoystickButton(xboxController, Constants.r2Button);
    Button r3Button = new JoystickButton(xboxController, Constants.r3Button);
    Button startButton = new JoystickButton(xboxController, Constants.startButton);





    private UI(){

    }

    public static UI getInstance(){
        return instance;
    }

    public double getRightXboxX(){
        double raw = xboxController.getX(Hand.kRight);
        return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
    }
    public double getRightXboxY(){
        double raw = xboxController.getY(Hand.kRight);
        return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
    }
    public double getLeftXboxX(){
        double raw = xboxController.getX(Hand.kLeft);
        return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
    }
    public double getLeftXboxY(){
        double raw = xboxController.getY(Hand.kLeft);
        return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
    }

    public double getLeftJoyX() {
        double raw = LEFT_JOY.getX();
        return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
    }

    public double getLeftJoyY() {
        double raw = LEFT_JOY.getY();
        return Math.abs(raw) < JOY_DEADZONE ? 0.0 : -raw;
    }

    public double getRightJoyX() {
        double raw = RIGHT_JOY.getX();
        return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
    }
    public double getRightJoyY() {
        double raw = RIGHT_JOY.getY();
        return Math.abs(raw) < JOY_DEADZONE ? 0.0 : -raw;
    }


    public static boolean getXboxA(){
        return xboxController.getAButton();
    }
    public static boolean getXboxX(){
        return xboxController.getXButton();
    }
    public static boolean getXboxY(){
        return xboxController.getYButton();
    }
    public static boolean getXboxB(){
        return xboxController.getBButton();
    }
    public static double getLeftJoystick(){
        return xboxController.getY(Hand.kLeft);
    }
    public static double getRightJoystick(){
        return xboxController.getY(Hand.kRight);
    }
    public static boolean getBackButtonPressed(){
        return xboxController.getBackButtonPressed();
    }

    public boolean getL3(){
        return l3Button.get();
    }

    public static XboxController getXboxController(){
        return UI.getXboxController();
    }
}
