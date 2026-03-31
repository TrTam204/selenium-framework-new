package framework.base;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;

import org.testng.ITestResult;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {

        boolean isCI = System.getenv("CI") != null;
        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            if (isCI) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);

        } else {

            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();

            if (isCI) {
                options.addArguments("-headless");

                // 🔥 FIX LỖI FIREFOX CI (QUAN TRỌNG)
                options.addPreference("dom.ipc.processCount", 1);
                options.addPreference("browser.tabs.remote.autostart", false);
            }

            driver = new FirefoxDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.google.com");

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

    private void takeScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.FILE);

            File dest = new File("target/screenshots/" + name + ".png");
            dest.getParentFile().mkdirs();

            Files.copy(src.toPath(), dest.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}