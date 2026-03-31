package tests;

import framework.models.UserData;
import framework.utils.JsonReader;
import framework.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserLoginTest {

    // ================= DATA PROVIDER (JSON) =================
    @DataProvider(name = "jsonUsers")
    public Object[][] getUsers() {
        return JsonReader.readUsers()
                .stream()
                .map(u -> new Object[]{u})
                .toArray(Object[][]::new);
    }

    // ================= TEST LOGIN (JSON DDT) =================
    @Test(dataProvider = "jsonUsers")
    public void testLogin(UserData user) {
        System.out.println("TEST CASE: " + user.getDescription());

        // Giả lập logic login
        boolean result =
                user.getUsername().equals("standard_user") &&
                user.getPassword().equals("secret_sauce");

        // xử lý user bị khóa
        if (user.getUsername().equals("locked_out_user")) {
            result = false;
        }

        // assert theo expectSuccess từ JSON
        if (user.isExpectSuccess()) {
            Assert.assertTrue(result, user.getDescription());
        } else {
            Assert.assertFalse(result, user.getDescription());
        }
    }

    // ================= TEST FAKER =================
    @Test
    public void testFaker() {
        var data = TestDataFactory.randomCheckoutData();

        System.out.println("FAKER DATA: " + data);

        Assert.assertNotNull(data);
        Assert.assertTrue(data.containsKey("firstName"));
        Assert.assertTrue(data.containsKey("lastName"));
        Assert.assertTrue(data.containsKey("postalCode"));
    }
}