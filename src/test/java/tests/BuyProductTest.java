package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ExtentTestManager;

public class BuyProductTest extends BaseTest {

    @Test(description = "Verify user can successfully buy a product")
    public void buyProductTest() {

        ExtentTestManager.getTest().info("Starting test");

        LoginPage login = new LoginPage(driver);
        login.login(
                config.getProperty("username"),
                config.getProperty("password")
        );

        ExtentTestManager.getTest().pass("Login successful");

        ProductsPage product = new ProductsPage(driver);
        product.addProductToCart();

        ExtentTestManager.getTest().pass("Product added");

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        ExtentTestManager.getTest().info("Checkout started");

        String firstName = config.getProperty("firstName");
        String lastName = config.getProperty("lastName");
        String zipCode = config.getProperty("zipCode");

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.completeOrder(firstName, lastName, zipCode);

        Assert.assertTrue(checkout.isOrderSuccessful());

        ExtentTestManager.getTest().pass("Order completed ✅");
    }
}