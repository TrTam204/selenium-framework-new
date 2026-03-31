package tests;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginSuccess() {
        Assert.assertTrue(
                new LoginPage(getDriver())
                        .login("standard_user", "secret_sauce")
                        .isLoaded()
        );
    }

    @Test
    public void testLoginWrongPassword() {
        boolean loaded =
                new LoginPage(getDriver())
                        .login("standard_user", "wrong")
                        .isLoaded();

        Assert.assertFalse(loaded);
    }

    @Test
    public void testLoginEmpty() {
        boolean loaded =
                new LoginPage(getDriver())
                        .login("", "")
                        .isLoaded();

        Assert.assertFalse(loaded);
    }
}