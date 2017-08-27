package br.com.aledsz.rarframework.database.exceptions;

import br.com.aledsz.rarframework.logging.log.LogManager;
import java.io.IOException;

/**
 * @description Log all exception error into file, using LogManager
 * @version 1.0.0.0
 * @author aleDsz
 */
public class RarFrameworkException {

    public static void LogError(Exception ex) throws IOException {
        String messageToLog = String.format("Ocorreu um erro na linha %d no arquivo %s\r\n", ex.getStackTrace()[0].getLineNumber(), ex.getStackTrace()[0].getFileName());

        for (StackTraceElement element : ex.getStackTrace()) {
            messageToLog += String.format("Ocorreu uma exception na linha %d na classe %s na função %s\r\n", element.getLineNumber(), element.getClassName(), element.getMethodName());
        }

        LogManager.LogTrace(messageToLog);
    }
}
