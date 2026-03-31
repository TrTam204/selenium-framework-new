package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTest;

public class SampleTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testPassDemo() {
        Assert.assertTrue(true);
    }
}