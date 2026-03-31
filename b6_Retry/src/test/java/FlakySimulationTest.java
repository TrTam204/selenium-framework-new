import org.testng.Assert;
import org.testng.annotations.Test;

public class FlakySimulationTest {

    private static int callCount = 0;

    @Test(description = "fail 2 lan dau, pass lan 3")
    public void testFlakyScenario() {

        callCount++;

        System.out.println("[FlakyTest] Lan: " + callCount);

        if (callCount <= 2) {
            Assert.fail("Fail lan " + callCount);
        }

        Assert.assertTrue(true);
    }
}