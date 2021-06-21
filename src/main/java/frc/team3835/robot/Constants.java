// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3835.robot;

import edu.wpi.first.wpilibj.XboxController;

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
    public static final int shooterExitVelocityMotor = 40;//CAN
    public static final int shooterUpSwitch = 0;//DIO
    public static final int shooterDownSwitch = 9;//DIO



    //controllers ports
    public static final int Xbox = 2;

    //shooter constants
    public static final double SHOOTER_ANGLE_VELOCITY = 0.6;//prec
    public static final double SHOOTER_ANGLE_DEAD_ZONE = 1;//deg
    public static final double SHOOTER_VELOCITY_TOLERANCE = 200;//rpm TODO: find Tolerance
    public static final double SHOOTER_VELOCITY_AT_12V = 6000;//rpm TODO: find
    public static final double SHOOTER_VELOCITY_WHEEL_DIAMETER = 4*0.0254;
    public static final double SHOOTER_VELOCITY_WHEEL_REDUCTION = 1;
    public static final double SHOOTER_VELOCITY_CONVERTER_CONSTANT = 60/(2*Math.PI*(SHOOTER_VELOCITY_WHEEL_DIAMETER/2));// from liner velocity to rotational velocity
    public static final double SHOOTER_VELOCITY_CONSTANT = 1;
    public static final double SHOOTER_TRIANGLE_SHOT_VELOCITY = 0;//m/s TODO: set up values
    public static final double SHOOTER_TRIANGLE_SHOT_ANGLE = 0;//deg
    public static final double SHOOTER_STORAGE_MOVING_TIME = 4;//sec

    //intake constants
    public static final double INTAKE_POWERUP = 0.7;
    public static final double INTAKE_POWERDOWN = 0.4;
    public static final double INTAKE_LIMIT_WHEN_SHOOTER_ANGLE_IS_LOW = 25;//deg
    public static final double INTAKE_POWER_TAKE = 0.8;//deg

    //storage constants
    public static final double STORAGE_SENSOR_BALL_UP_BOUND = 2.8;
    public static final double STORAGE_SENSOR_BALL_DOWN_BOUND = 2.2;
    public static final double STORAGE_POWER = 1;

    //robot dimensions
    public static final double SHOOTER_LENGTH = 0.45; //m TODO: find
    public static final double SHOOTER_HIGHT = 0.2; //m TODO: find
    public static final double CAMERA_HIGHT = 0.4;//m TODO: find
    public static final double CAMERA_ANGLE = 30;//deg TODO: find
}