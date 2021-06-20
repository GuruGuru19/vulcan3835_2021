package frc.team3835.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team3835.robot.commands.AutoShootCommand;
import frc.team3835.robot.commands.TranchRunCommand;
import frc.team3835.robot.commands.TriangleShootCommand;

public class UI {

    private static UI instance = new UI();

    public static final double JOY_DEADZONE = 0.08;

    public static final XboxController xboxController = new XboxController(Constants.Xbox);

    private static Button aButton = new JoystickButton(xboxController, XboxController.Button.kA.value);
    private static Button bButton = new JoystickButton(xboxController, XboxController.Button.kB.value);
    private static Button xButton = new JoystickButton(xboxController, XboxController.Button.kX.value);
    private static Button yButton = new JoystickButton(xboxController, XboxController.Button.kY.value);
    private static Button lbButton = new JoystickButton(xboxController, XboxController.Button.kBumperLeft.value);
    private static Button lsButton = new JoystickButton(xboxController, XboxController.Button.kStickLeft.value);
    private static Button rbButton = new JoystickButton(xboxController, XboxController.Button.kBumperRight.value);
    private static Button rsButton = new JoystickButton(xboxController, XboxController.Button.kStickRight.value);
    private static Button startButton = new JoystickButton(xboxController, XboxController.Button.kStart.value);
    private static Button backButton = new JoystickButton(xboxController, XboxController.Button.kBack.value);







    private UI(){
        xButton.whenPressed(new AutoShootCommand());
        bButton.whenPressed(new TriangleShootCommand());
        yButton.whenPressed(new TranchRunCommand());
    }

    public static UI getInstance(){
        return instance;
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

    public static double getLeftJoystickY(){
        if (Math.abs(xboxController.getY(Hand.kLeft))<JOY_DEADZONE){
            return xboxController.getY(Hand.kLeft);
        }
        return 0;
    }
    public static double getRightJoystickY(){
        if (Math.abs(xboxController.getY(Hand.kRight))<JOY_DEADZONE){
            return xboxController.getY(Hand.kRight);
        }
        return 0;
    }
    public static double getLeftJoystickX(){
        if (Math.abs(xboxController.getX(Hand.kLeft))<JOY_DEADZONE){
            return xboxController.getX(Hand.kLeft);
        }
        return 0;
    }
    public static double getRightJoystickX(){
        if (Math.abs(xboxController.getX(Hand.kRight))<JOY_DEADZONE){
            return xboxController.getX(Hand.kRight);
        }
        return 0;
    }

    public static boolean getBackButtonPressed(){
        return xboxController.getBackButton();
    }

    public static boolean getL3(){
        return xboxController.getStickButton(Hand.kLeft);
    }

    public static XboxController getXboxController(){
        return xboxController;
    }
}
