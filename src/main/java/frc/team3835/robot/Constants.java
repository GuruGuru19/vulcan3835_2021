// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3835.robot;

public final class Constants {
    //CAN address
    public static final int driveSubLeftFront= 0;//drive base subsystem
    public static final int driveSubLeftBack= 0;
    public static final int driveSubRightFront= 0;
    public static final int driveSubRightBack= 0;

    public static final int climbMotor = 0;//climb base subsystem

    public static final int intakeAngleMotor = 0;//intake subsystem
    public static final int intakePowerMotor = 0;
    public static final int intakeLimitSwitchDown = 0;
    public static final int intakeLimitSwitchUp = 0;

    public static final int storageMotor = 0;//storage subsystem
    public static final int storageSensor = 0;
    public static final int[] storageEncoder = {0,0};


    public static final int shooterAngleMotor = 0;//shooter subsystem
    public static final int[] shooterAngleEncoder = {0,0};
    public static final int shooterExitVelocityMotor = 0;

    //controllers ports
    public static final int LeftJoystick = 1;
    public static final int RightJoystick = 0;
    public static final int Xbox = 2;
    public static final int aButton = 1;
    public static final int bButton = 2;
    public static final int xButton = 3;
    public static final int yButton = 4;
    public static final int l1Button = 5;
    public static final int l2Button = 0;
    public static final int l3Button = 9;
    public static final int r1Button = 6;
    public static final int r2Button = 0;
    public static final int r3Button = 10;
    public static final int rXButton = 0;
    public static final int startButton = 8;
    public static final int backButton = 7;
    public static final int downButton = 0;

    //shooter constants
    public static final double SHOOTER_ANGLE_VELOCITY = 0.6;//prec
    public static final double SHOOTER_ANGLE_DEAD_ZONE = 2;//deg

    //intake constants
    public static final double INTAKE_POWERUP = 0.7;
    public static final double INTAKE_POWERDOWN = 0.4;


    

}