// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3835.robot;

import edu.wpi.first.wpilibj.XboxController;

import java.io.File;

public final class Constants {

    public static final File loggerPath = new File("");//TODO: find
    public static final boolean enableLogger = false;

    //ports address
    //drive base subsystem
    public static final int driveSubLeftFront= 12;//CAN
    public static final int driveSubLeftBack= 13;//CAN
    public static final int driveSubRightFront= 14;//CAN
    public static final int driveSubRightBack= 15;//CAN

    //climb base subsystem
    public static final int climbMotor = 20;//CAN
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
    public static final int shooterAngleMotor = 23;//CAN
    public static final int shooterExitVelocityMotor = 40;//CAN
    public static final int shooterUpSwitch = 0;//DIO
    public static final int shooterDownSwitch = 9;//DIO
    public static final int[] shooterEncoder = {8,7};//DIO



    //controllers ports
    public static final int Xbox = 0;

    //shooter constants
    public static final double SHOOTER_ANGLE_VELOCITY = 0.85;//prec
    public static final double SHOOTER_ANGLE_DEAD_ZONE = 5;//deg
    public static final double SHOOTER_VELOCITY_TOLERANCE = 100;//rpm
    public static final double SHOOTER_VELOCITY_AT_1V = 445;//rpm
    public static final double SHOOTER_VELOCITY_WHEEL_DIAMETER = 4*0.0254;
    public static final double SHOOTER_VELOCITY_WHEEL_REDUCTION = 1;
    public static final double SHOOTER_VELOCITY_CONVERTER_CONSTANT = 60/(2*Math.PI*(SHOOTER_VELOCITY_WHEEL_DIAMETER/2));// from liner velocity to rotational velocity
    public static final double SHOOTER_VELOCITY_CONSTANT = 1;
    public static final double SHOOTER_TRIANGLE_SHOT_VELOCITY = 5.8;//m/s TODO: set up values
    public static final double SHOOTER_TRIANGLE_SHOT_ANGLE = 79;//deg
    public static final double SHOOTER_STORAGE_MOVING_TIME = 4;//sec

    //intake constants
    public static final double INTAKE_POWERUP = 0.9;
    public static final double INTAKE_POWERDOWN = 0.4;
    public static final double INTAKE_LIMIT_WHEN_SHOOTER_ANGLE_IS_LOW = 25;//deg
    public static final double INTAKE_POWER_TAKE = 0.8;//deg

    //storage constants
    public static final double STORAGE_SENSOR_BALL_UP_BOUND = 3;//Volts
    public static final double STORAGE_SENSOR_BALL_DOWN_BOUND = 2;//Volts
    public static final double STORAGE_POWER = 0.7;

    //drive constants
    public static final double DRIVE_TURN_PID_KP = 0.05;//TODO: set up
    public static final double DRIVE_TURN_PID_KI = 0;
    public static final double DRIVE_TURN_PID_KD = 0;
    public static final double DRIVE_TURN_PID_P_TOLERANCE = 1;//deg
    public static final double DRIVE_TURN_PID_V_TOLERANCE = 0;//deg/s
    public static final double DRIVE_TURN_ANGLE_TOLERANCE = 3;//deg
    public static final double DRIVE_SLOW_TURN_POWER = 0.08;//prc

    //robot dimensions
    public static final double SHOOTER_LENGTH = 0.6; //m TODO: find
    public static final double SHOOTER_HIGHT = 0.268; //m TODO: find
    public static final double CAMERA_HIGHT = 0.64;//m TODO: find
    public static final double CAMERA_ANGLE = 34;//deg TODO: find
    public static final double CAMERA_DISTANCE_FROM_MID = 0.291;//m
    public static final double CAMERA_DISTANCE_FROM_SHOOTER_X = 0.4;//m
    public static final double SHOOTER_ANGLE_OFFSET_FROM_MID = 6;//m
}