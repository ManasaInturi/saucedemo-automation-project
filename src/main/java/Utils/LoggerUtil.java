package Utils;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggerUtil {

    private static Logger logger;

    // Initialize logger with current class
    public static void init(Class<?> clazz) {
        logger = LogManager.getLogger(clazz);
    }

    public static void logInfo(String message) {
        logger.info(message);
        ExtentReportsManager.getTest().log(Status.INFO, message);
    }

    public static void logDebug(String message) {
        logger.debug(message);
        ExtentReportsManager.getTest().log(Status.INFO, message);
    }

    public static void logWarn(String message) {
        logger.warn(message);
        ExtentReportsManager.getTest().log(Status.WARNING, message);
    }

    public static void logError(String message) {
        logger.error(message);
        ExtentReportsManager.getTest().log(Status.FAIL, message);
    }

    public static void logFail(String message) {
        logger.error("FAIL: " + message);
        ExtentReportsManager.getTest().fail(message);
    }

    public static void logPass(String message) {
        logger.info("PASS: " + message);
        ExtentReportsManager.getTest().pass(message);
    }
}
