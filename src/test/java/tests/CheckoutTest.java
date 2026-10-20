package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CheckoutTest extends BaseTest {

    @Test(description = "Complete end-to-end purchase")
    public void completeCheckoutTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addBackpackToCart();
        inventory.goToCart();

        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.getItemCount(), 1);
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillCustomerInfo("Shakshi", "Rawat", "248001");
        Assert.assertTrue(checkout.getTotalText().contains("Total"));
        checkout.clickFinish();

        Assert.assertEquals(checkout.getConfirmationMessage(), "Thank you for your order!");
    }

    @Test(description = "Cart shows the correct product name")
    public void cartShowsCorrectItemTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addBackpackToCart();
        inventory.goToCart();

        Assert.assertTrue(new CartPage(driver).getItemNames().contains("Sauce Labs Backpack"));
    }

    @Test(description = "Checkout without first name shows error")
    public void checkoutWithoutFirstNameTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addBackpackToCart();
        inventory.goToCart();
        new CartPage(driver).clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clickContinueOnly();
        Assert.assertTrue(checkout.getErrorMessage().contains("First Name is required"));
    }
}