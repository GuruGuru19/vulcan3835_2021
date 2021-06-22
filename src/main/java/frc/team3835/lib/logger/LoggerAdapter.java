package frc.team3835.lib.logger;

import frc.team3835.robot.Constants;

import java.io.File;

public class LoggerAdapter {
    private static Logger logger;

    public static void loggerInit(File path){
        logger = new Logger(path);
    }

    public static void log(String log){
        if (logger != null) {
            logger.log(log);
        }
        else {
            System.out.println("logger.log() didn't work because logger is not initialized");
        }
    }

    public static void saveLog(){
        if(logger != null){
            logger.saveLog();
        }
        else {
            System.out.println("logger.saveLog() didn't work because logger is not initialized");
        }
    }
}
