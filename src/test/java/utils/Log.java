package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    public static Logger logger = LogManager.getLogger();

    // ✅ Wait method
    public static void waitTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ✅ Step logging method
    public static void logStep(String message) {
        logger.info(message);          // ✅ log4j log
        System.out.println(message);   // ✅ console log (optional)

        // ✅ If using Extent Report (optional)
        try {
            ExtentTestManager.getTest().info(message);
        } catch (Exception e) {
            // ignore if extent not initialized
        }

        waitTime(2);  // ⏱️ delay for demo
    }
}