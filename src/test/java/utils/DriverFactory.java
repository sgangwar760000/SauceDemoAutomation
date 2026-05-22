package utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // ✅ Disable notifications & popups
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");

            // ✅ VERY IMPORTANT → remove password popup
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-infobars");

            // ✅ Disable Chrome password manager
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            // ✅ Optional but recommended
            options.addArguments("--incognito");

            driver.set(new ChromeDriver(options));
        }

        // ✅ Common settings
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();  // ✅ fresh session

        // ✅ Default wait
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}