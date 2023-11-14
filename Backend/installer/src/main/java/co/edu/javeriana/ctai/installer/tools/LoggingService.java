package co.edu.javeriana.ctai.installer.tools;

import java.io.IOException;
import java.util.logging.*;

public class LoggingService {
    private static final Logger LOGGER = Logger.getLogger(LoggingService.class.getName());
    private static LoggingService instance;
    public LoggingService() {
        try {
            FileHandler fileHandler = new FileHandler("logs/TG9.log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al configurar el archivo de registro", e);
        }
    }

    public static LoggingService getInstance() {
        if (instance == null) {
            synchronized (LoggingService.class) {
                if (instance == null) {
                    instance = new LoggingService();
                }
            }
        }
        return instance;
    }

    public void logInfo(String message) {
        LOGGER.info(message);
    }

    public void logWarning(String message) {
        LOGGER.warning(message);
    }

    public void logError(String message) {
        LOGGER.severe(message);
    }

    public void close() {
        for (Handler handler : LOGGER.getHandlers()) {
            handler.close();
        }
    }
}

