package tests;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import framework.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void testAddItem() {
        CartPage cart =
                new LoginPage(getDriver())
                        .login("standard_user", "secret_sauce")
                        .addFirstItemToCart()
                        .goToCart();

        Assert.assertTrue(cart.getItemCount() > 0);
    }

    @Test
    public void testRemoveItem() {
        CartPage cart =
                new LoginPage(getDriver())
                        .login("standard_user", "secret_sauce")
                        .addFirstItemToCart()
                        .goToCart()
                        .removeFirstItem();

        Assert.assertEquals(cart.getItemCount(), 0);
    }

    @Test
    public void testEmptyCart() {
        CartPage cart =
                new LoginPage(getDriver())
                        .login("standard_user", "secret_sauce")
                        .goToCart();

        Assert.assertEquals(cart.getItemCount(), 0);
    }
}