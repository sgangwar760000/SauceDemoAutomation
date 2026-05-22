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

public class CheckoutPage {

    private WebDriver driver;
    private Properties prop;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.prop = PropertyReader.getProperties("locators.properties");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

   
    public void completeOrder(String firstName, String lastName, String zipCode) {

        Log.logger.info("Waiting for Checkout Page");
        ExtentTestManager.getTest().info("Waiting for Checkout Page to load");

        wait.until(ExpectedConditions.urlContains("checkout"));

        Log.logger.info("Entering First Name");
        ExtentTestManager.getTest().info("Entering First Name");

        By firstNameLocator = LocatorUtil.getLocator(prop.getProperty("firstName"));
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameLocator));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        Log.logger.info("Entering Last Name");
        ExtentTestManager.getTest().info("Entering Last Name");

        By lastNameLocator = LocatorUtil.getLocator(prop.getProperty("lastName"));
        WebElement lastNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameLocator));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        Log.logger.info("Entering Zip Code");
        ExtentTestManager.getTest().info("Entering Zip Code");

        By zipLocator = LocatorUtil.getLocator(prop.getProperty("zipCode"));
        WebElement zipField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(zipLocator));
        zipField.clear();
        zipField.sendKeys(zipCode);

        Log.logger.info("Clicking Continue Button");
        ExtentTestManager.getTest().info("Clicking Continue button");

        By continueLocator = LocatorUtil.getLocator(prop.getProperty("continueButton"));
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(continueLocator));
        continueBtn.click();

        Log.logger.info("Clicking Finish Button");
        ExtentTestManager.getTest().info("Clicking Finish button");

        By finishLocator = LocatorUtil.getLocator(prop.getProperty("finish"));
        WebElement finishBtn = wait.until(
                ExpectedConditions.elementToBeClickable(finishLocator));
        finishBtn.click();

        Log.logger.info("Order completed successfully");
        ExtentTestManager.getTest().pass("Order completed successfully");
    }
    

    public boolean isOrderSuccessful() {

        ExtentTestManager.getTest().info("Verifying order success message");

        By successLocator = LocatorUtil.getLocator(prop.getProperty("success"));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successLocator))
                .isDisplayed();
    }
}