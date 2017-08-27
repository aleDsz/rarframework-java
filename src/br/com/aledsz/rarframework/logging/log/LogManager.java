package br.com.aledsz.rarframework.logging.log;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description Log all package trace
 * @version 1.0.0.0
 * @author aleDsz
 */
public class LogManager {

    private static final String logFileName = "RarFrameworkLog.txt";

    private static void Log(String logType, String logMessage) throws IOException {
        File appPath = new File(new File(".").getAbsolutePath());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[0];

        String logData = String.format("[%s] [%s::%s] [%s] - %s\r\n", dateFormat.format(new Date()), stackTraceElement.getClassName(), stackTraceElement.getMethodName(), logType, logMessage);

        try (Writer logFile = new BufferedWriter(new FileWriter(String.format("%s/%s", appPath.getCanonicalPath(), logFileName), true))) {
            logFile.append(logData);
        }
    }

    public static void LogTrace(String logMessage) throws IOException {
        Log("TRACE", logMessage);
    }

    public static void LogError(String logMessage) throws IOException {
        Log("ERROR", logMessage);
    }
}
