package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class BuyProductTest extends BaseTest {

    @Test(description = "Verify user can successfully buy a product")
    public void buyProductTest() {

        // ✅ Login
        LoginPage login = new LoginPage(driver);

        login.login(
                config.getProperty("username"),
                config.getProperty("password")
        );

        // ✅ Add Product to Cart
        ProductsPage product = new ProductsPage(driver);
        product.addProductToCart();

        // ✅ Navigate to Checkout
        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        // ✅ Read data from config (CORRECT KEYS ✅)
        String firstName = config.getProperty("firstName");
        String lastName = config.getProperty("lastName");
        String zipCode = config.getProperty("zipCode");

        // ✅ Debug check (optional but useful)
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Zip Code: " + zipCode);

        // ✅ Complete Order
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.completeOrder(firstName, lastName, zipCode);

        // ✅ Validation
        Assert.assertTrue(
                checkout.isOrderSuccessful(),
                "❌ Order was not successful"
        );
    }
}