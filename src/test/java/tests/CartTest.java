package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest extends BaseTest {

    @Test
    public void addSingleItemToCartTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage(driver);

        inventory.addBackpackToCart();
        Assert.assertEquals(inventory.getCartCount(), "1");
    }

    @Test
    public void addMultipleItemsToCartTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage(driver);

        inventory.addBackpackToCart();
        inventory.addBikeLightToCart();
        Assert.assertEquals(inventory.getCartCount(), "2");
    }

    @Test
    public void removeItemFromCartTest() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage(driver);

        inventory.addBackpackToCart();
        inventory.removeBackpackFromCart();
        Assert.assertFalse(inventory.isCartBadgeDisplayed());
    }
}