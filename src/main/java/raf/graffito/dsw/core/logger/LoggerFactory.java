package raf.graffito.dsw.core.logger;

public class LoggerFactory {
    public Logger createLogger(String loggerName) {
        if(loggerName.equalsIgnoreCase("consolelogger")) {
            return new ConsoleLogger();
        } else if(loggerName.equalsIgnoreCase("filelogger")) {
            return new FileLogger();
        } else {
            return null;
        }
    }

}
