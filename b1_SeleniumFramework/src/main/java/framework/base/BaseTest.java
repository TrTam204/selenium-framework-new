package framework.base;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return tlDriver.get();
    }


@Parameters({"browser", "env"})
@BeforeMethod
public void setUp(@Optional("chrome") String browser,
                  @Optional("dev") String env) {

    WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");        // 🔥 bắt buộc
    options.addArguments("--no-sandbox");          // 🔥 bắt buộc
    options.addArguments("--disable-dev-shm-usage"); // 🔥 bắt buộc
    options.addArguments("--disable-gpu");
    options.addArguments("--window-size=1920,1080");

    WebDriver driver = new ChromeDriver(options);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.get("https://www.saucedemo.com");

    tlDriver.set(driver);
}
    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }

        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }

    private void takeScreenshot(String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());

            File src = ((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.FILE);

            File dest = new File("target/screenshots/"
                    + testName + "_" + timestamp + ".png");

            dest.getParentFile().mkdirs();

            Files.copy(src.toPath(), dest.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}