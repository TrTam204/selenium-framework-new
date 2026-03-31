package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTest;

public class SampleTest extends BaseTest {

    @Test
    public void testGridDemo() {
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("saucedemo"));
    }
}