// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3835.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team3835.lib.logger.Logger;
import frc.team3835.lib.logger.LoggerAdapter;
import frc.team3835.robot.commands.Auto1CommandGroup;
import frc.team3835.robot.commands.AutoShootCommand;
import frc.team3835.robot.commands.ShooterResetCommand;
import frc.team3835.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * methods corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
    private boolean loggerInitialized;
    private Command autonomousCommand;

    private UI ui;
    private CameraSubsystem cameraSubsystem;
    private ClimbSubsystem climbSubsystem;
    private DriveSubsystem driveSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private StorageSubsystem storageSubsystem;

    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit()
    {
        autonomousCommand = null;

        cameraSubsystem = CameraSubsystem.getInstance();
        shooterSubsystem = ShooterSubsystem.getInstance();
        climbSubsystem = ClimbSubsystem.getInstance();
        driveSubsystem = DriveSubsystem.getInstance();
        intakeSubsystem = IntakeSubsystem.getInstance();
        storageSubsystem = StorageSubsystem.getInstance();
        ui = new UI(shooterSubsystem,storageSubsystem,driveSubsystem,intakeSubsystem);
        loggerInitialized = false;
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.

    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic()
    {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /** This method is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
        LoggerAdapter.log("robot disabled");
        LoggerAdapter.saveLog();
        loggerInitialized = false;
    }

    @Override
    public void disabledPeriodic() {}

    @Override
    public void autonomousInit()
    {
        if (!loggerInitialized && Constants.enableLogger){
            LoggerAdapter.loggerInit(Constants.loggerPath);
        }
        LoggerAdapter.log("robot at autonomous time");
        autonomousCommand = new Auto1CommandGroup(shooterSubsystem, storageSubsystem, driveSubsystem);

        // schedule the autonomous command (example)
        if (autonomousCommand != null)
        {
            autonomousCommand.schedule();
        }
    }

    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit()
    {
        if (!loggerInitialized && Constants.enableLogger){
            LoggerAdapter.loggerInit(Constants.loggerPath);
        }
        LoggerAdapter.log("robot at teleop time");
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
        {
            autonomousCommand.cancel();
        }
        else{
            new ShooterResetCommand().withTimeout(3).schedule();
        }
    }

    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {}

    @Override
    public void testInit()
    {
        if (!loggerInitialized){
            LoggerAdapter.loggerInit(Constants.loggerPath);
        }
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
