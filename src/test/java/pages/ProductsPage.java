package pages;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import utils.ExtentTestManager;
import utils.LocatorUtil;
import utils.PropertyReader;

public class ProductsPage {

    private WebDriver driver;
    private Properties prop;
    private WebDriverWait wait;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.prop = PropertyReader.getProperties("locators.properties");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addProductToCart() {

        Log.logger.info("Waiting for Products Page");
        ExtentTestManager.getTest().info("Waiting for Products Page to load");

        wait.until(ExpectedConditions.urlContains("inventory"));

        Log.logger.info("Clicking Add to Cart");
        ExtentTestManager.getTest().info("Clicking Add to Cart button");

        By addToCartLocator = LocatorUtil.getLocator(prop.getProperty("addToCart"));
        WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCartBtn.click();

        Log.logger.info("Opening Cart");
        ExtentTestManager.getTest().info("Navigating to Cart");

        By cartLocator = LocatorUtil.getLocator(prop.getProperty("cart"));
        WebElement cartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(cartLocator));
        cartBtn.click();

        Log.logger.info("Product added to cart");
        ExtentTestManager.getTest().pass("Product added to cart successfully");
    }
}