package pages;

import java.time.Duration;
import java.util.Properties;
import utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentTestManager;
import utils.LocatorUtil;
import utils.PropertyReader;

public class LoginPage {

    private WebDriver driver;
    private Properties prop;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.prop = PropertyReader.getProperties("locators.properties");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String username, String password) {

        Log.logger.info("Waiting for Login Page");
        ExtentTestManager.getTest().info("Waiting for Login Page");

        By usernameLocator = LocatorUtil.getLocator(prop.getProperty("username"));

        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameLocator));

        Log.logger.info("Entering Username");
        ExtentTestManager.getTest().info("Entering Username");

        usernameField.clear();
        usernameField.sendKeys(username);

        By passwordLocator = LocatorUtil.getLocator(prop.getProperty("password"));

        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordLocator));

        Log.logger.info("Entering Password");
        ExtentTestManager.getTest().info("Entering Password");

        passwordField.clear();
        passwordField.sendKeys(password);

        Log.logger.info("Clicking Login Button"); 
        ExtentTestManager.getTest().info("Clicking Login Button"); 

        By loginBtnLocator = LocatorUtil.getLocator(prop.getProperty("loginButton"));

        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(loginBtnLocator));

        loginBtn.click();

        Log.logger.info("Login successful");
        ExtentTestManager.getTest().pass("Login successful");
    }
}