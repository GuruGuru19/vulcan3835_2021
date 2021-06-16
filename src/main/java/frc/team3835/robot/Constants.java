// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3835.robot;

public final class Constants {
    //ports address
    //drive base subsystem
    public static final int driveSubLeftFront= 0;//CAN
    public static final int driveSubLeftBack= 0;//CAN
    public static final int driveSubRightFront= 0;//CAN
    public static final int driveSubRightBack= 0;//CAN

    //climb base subsystem
    public static final int climbMotor = 0;//CAN
    public static final int climbSwitch = 2;//DIO

    //intake subsystem
    public static final int intakeAngleMotor = 26;//CAN
    public static final int intakePowerMotor = 31;//CAN
    public static final int intakeLimitSwitchDown = 1;//DIO
    public static final int intakeLimitSwitchUp = 3;//DIO

    //storage subsystem
    public static final int storageMotorUp = 24;//CAN
    public static final int storageMotorDown = 30;//CAN
    public static final int storageSensor = 0;//analog in 0 :)


    //shooter subsystem
    public static final int shooterAngleMotor = 0;//CAN
    public static final int[] shooterAngleEncoder = {0,0};
    public static final int shooterExitVelocityMotor = 40;//CAN
    public static final int shooterUpSwitch = 0;//DIO
    public static final int shooterDownSwitch = 9;//DIO



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
    public static final double SHOOTER_ANGLE_DEAD_ZONE = 1;//deg
    public static final double SHOOTER_VELOCITY_KP = 0;
    public static final double SHOOTER_VELOCITY_KI = 0;
    public static final double SHOOTER_VELOCITY_KD = 0;
    public static final double SHOOTER_VELOCITY_WHEEL_DIAMETER = 4*0.0254;
    public static final double SHOOTER_VELOCITY_WHEEL_REDUCTION = 1;
    public static final double SHOOTER_VELOCITY_CONVERTER_CONSTANT = 60/(2*Math.PI*(SHOOTER_VELOCITY_WHEEL_DIAMETER/2));// from liner velocity to rotational velocity
    public static final double SHOOTER_VELOCITY_CONSTANT = 1;

    //intake constants
    public static final double INTAKE_POWERUP = 0.7;
    public static final double INTAKE_POWERDOWN = 0.4;
    public static final double INTAKE_LIMIT_WHEN_SHOOTER_ANGLE_IS_LOW = 25;//deg
    public static final double INTAKE_POWER_TAKE = 0.8;//deg

    //storage constants
    public static final double STORAGE_SENSOR_BALL_UP_BOUND = 2.8;
    public static final double STORAGE_SENSOR_BALL_DOWN_BOUND = 2.2;
    public static final double STORAGE_POWER = 2.2;


}