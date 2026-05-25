package pages;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;
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

        Log.logStep("Waiting for Products Page");
        wait.until(ExpectedConditions.urlContains("inventory"));

        Log.logStep("Clicking Add to Cart");

        By addToCartLocator = LocatorUtil.getLocator(prop.getProperty("addToCart"));
        WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addToCartLocator));
        addToCartBtn.click();

        Log.logStep("Opening Cart");

        By cartLocator = LocatorUtil.getLocator(prop.getProperty("cart"));
        WebElement cartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(cartLocator));
        cartBtn.click();

        Log.logStep("Product added to cart");
    }
}