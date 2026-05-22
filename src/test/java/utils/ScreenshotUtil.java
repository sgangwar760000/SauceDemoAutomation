package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String testName) {

        String dirPath = System.getProperty("user.dir") + "/test-output/screenshots/";
        new File(dirPath).mkdirs();  // ✅ ensure folder exists

        String path = dirPath + testName + ".png";

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}