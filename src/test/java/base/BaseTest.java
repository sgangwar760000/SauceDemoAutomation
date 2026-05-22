package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.DriverFactory;
import utils.PropertyReader;

public class BaseTest {

    protected WebDriver driver;
    protected Properties config;

    @BeforeMethod
    public void setup() {

        config = PropertyReader.getProperties("config.properties");

        driver = DriverFactory.initDriver(config.getProperty("browser"));

        driver.get(config.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();  // ✅ correct cleanup
    }
}