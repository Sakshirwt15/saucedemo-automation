package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products");
    }

    @Test(priority = 2)
    public void invalidPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_pass");

        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"));
    }

    @Test(priority = 3)
    public void lockedOutUserTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }
}