package raf.graffito.dsw.logger;

public class LoggerFactory {

    public enum LoggerType {
        CONSOLE, FILE;
    }

    public static Logger napraviLogger(LoggerType type) {
        switch (type) {
            case CONSOLE:
                return new ConsoleLogger();
            case FILE:
                return new FileLogger();
            default:
                throw new IllegalArgumentException("Nepoznati tip loggera " + type.name());
        }

    }

}
