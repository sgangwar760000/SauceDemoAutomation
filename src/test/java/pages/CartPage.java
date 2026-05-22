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

public class CartPage {

    private WebDriver driver;
    private Properties prop;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.prop = PropertyReader.getProperties("locators.properties");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickCheckout() {

        Log.logger.info("Waiting for Cart Page");
        ExtentTestManager.getTest().info("Waiting for Cart Page to load");

        wait.until(ExpectedConditions.urlContains("cart"));

        Log.logger.info("Clicking Checkout Button");
        ExtentTestManager.getTest().info("Clicking Checkout button");

        By checkoutLocator = LocatorUtil.getLocator(prop.getProperty("checkout"));

        WebElement checkoutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(checkoutLocator)
        );

        checkoutBtn.click();

        Log.logger.info("Checkout clicked");
        ExtentTestManager.getTest().pass("Checkout button clicked successfully");
    }
}