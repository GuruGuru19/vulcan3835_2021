package frc.team3835.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team3835.robot.commands.*;
import frc.team3835.robot.subsystems.DriveSubsystem;
import frc.team3835.robot.subsystems.IntakeSubsystem;
import frc.team3835.robot.subsystems.ShooterSubsystem;
import frc.team3835.robot.subsystems.StorageSubsystem;

public class UI {

    private static UI instance;

    public static final double JOY_DEADZONE = 0.08;

    public static final XboxController xboxController = new XboxController(Constants.Xbox);
    public static final XboxController xboxController2 = new XboxController(Constants.Xbox2);

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

    private static Button aButton2 = new JoystickButton(xboxController2, XboxController.Button.kA.value);
    private static Button bButton2 = new JoystickButton(xboxController2, XboxController.Button.kB.value);
    private static Button xButton2 = new JoystickButton(xboxController2, XboxController.Button.kX.value);
    private static Button yButton2 = new JoystickButton(xboxController2, XboxController.Button.kY.value);
    private static Button lbButton2 = new JoystickButton(xboxController2, XboxController.Button.kBumperLeft.value);
    private static Button lsButton2 = new JoystickButton(xboxController2, XboxController.Button.kStickLeft.value);
    private static Button rbButton2 = new JoystickButton(xboxController2, XboxController.Button.kBumperRight.value);
    private static Button rsButton2 = new JoystickButton(xboxController2, XboxController.Button.kStickRight.value);
    private static Button startButton2 = new JoystickButton(xboxController2, XboxController.Button.kStart.value);
    private static Button backButton2 = new JoystickButton(xboxController2, XboxController.Button.kBack.value);







    public UI(ShooterSubsystem shooterSubsystem, StorageSubsystem storageSubsystem, DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem){

        xButton2.whenPressed(new ShooterResetCommand().withTimeout(3).andThen(
                new AutoShootCommand(shooterSubsystem, storageSubsystem, driveSubsystem).andThen(
                        new ShooterResetCommand().withTimeout(3))));
        bButton2.whenReleased(new ShooterResetCommand().andThen(
                new TriangleShootCommand(shooterSubsystem, storageSubsystem, driveSubsystem)));
        yButton2.whenPressed(new TranchRunCommand(intakeSubsystem, shooterSubsystem));
        aButton2.whenPressed(new ShooterResetCommand().withTimeout(3));

        System.out.println("UI ok");
        instance = this;
    }

    public static UI getInstance(){
        return instance;
    }


    public static boolean getXboxAorBump(){
        return xboxController.getAButton() || xboxController.getBumper(Hand.kRight) || xboxController .getBumper(Hand.kLeft);
    }

    public static double getLeftJoystickY(){
        if (Math.abs(xboxController.getY(Hand.kLeft))>JOY_DEADZONE){
            return -xboxController.getY(Hand.kLeft);
        }
        return 0;
    }
    public static double getRightJoystickY(){
        if (Math.abs(xboxController.getY(Hand.kRight))>JOY_DEADZONE){
            return -xboxController.getY(Hand.kRight);
        }
        return 0;
    }
    public static double getLeftJoystickX(){
        if (Math.abs(xboxController.getX(Hand.kLeft))>JOY_DEADZONE){
            return xboxController.getX(Hand.kLeft);
        }
        return 0;
    }
    public static double getRightJoystickX(){
        if (Math.abs(xboxController.getX(Hand.kRight))>JOY_DEADZONE){
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
    public static boolean getR3(){
        return xboxController.getStickButton(Hand.kRight);
    }

    public static XboxController getXboxController(){
        return xboxController;
    }

    public static XboxController getXbox2Controller(){
        return xboxController2;
    }
}
