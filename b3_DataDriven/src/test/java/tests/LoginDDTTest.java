package tests;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginDDTTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderClass.class)
    public void testLogin(String user, String pass, String expected, String desc) {

        System.out.println("TEST CASE: " + desc);

        boolean result = new LoginPage(getDriver())
                .login(user, pass)
                .isLoaded();

        if (expected.equals("success")) {
            Assert.assertTrue(result, desc);
        } else {
            Assert.assertFalse(result, desc);
        }
    }
}
