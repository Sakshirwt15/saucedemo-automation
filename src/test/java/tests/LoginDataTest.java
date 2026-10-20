package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginDataTest extends BaseTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"standard_user", "wrong_pass", "do not match"},
                {"wrong_user", "secret_sauce", "do not match"},
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"},
                {"locked_out_user", "secret_sauce", "locked out"}
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginTest(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError));
    }
}