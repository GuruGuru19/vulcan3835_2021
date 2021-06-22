package frc.team3835.lib.logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Logger {
    private StringBuilder sb = new StringBuilder();
    private File logFile;

    public Logger(File directory){
        String now = LocalDateTime.now().toString().replaceAll(":", ".");
        if (!directory.exists()){
            directory.mkdirs();
        }
        this.logFile = new File(directory, now+".log.zip");
    }

    private String format(String s){
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.getMonthValue() + "/" + now.getDayOfMonth() + " " + now.getHour() + ":" + now.getMinute() + ":" +  now.getSecond();//MM/DD h:m:s
        return "[" + currentTime + "] " + s;
    }

    public void log(String log){
        log = format(log);
        System.out.println(log);
        sb.append(log).append("\n");
    }

    public void saveLog(){
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(this.logFile));
            ZipEntry e = new ZipEntry(this.logFile.getName().replace(".zip", ""));

            zos.putNextEntry(e);
            zos.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            zos.flush();

            zos.closeEntry();
            zos.close();
            sb.setLength(0);
            System.out.println("Successfully saved logs!\n" +
                    "Path: " + this.logFile.getAbsolutePath());
        } catch (IOException e) {
            LoggerAdapter.log("Failed to save logs!\n" +
                    "Path: " + this.logFile.getAbsolutePath() + "\n" +
                    "Stacktrace: " + getStackTrace(e));
        }
    }
    private static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
