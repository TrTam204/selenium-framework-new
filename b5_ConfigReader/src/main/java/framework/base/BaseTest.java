package framework.base;

import framework.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @Parameters({"env"})
    @BeforeMethod
    public void setUp(@Optional("dev") String env) {

        System.setProperty("env", env);

        ConfigReader config = ConfigReader.getInstance();

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(config.getBaseUrl());

        System.out.println("Explicit wait = " + config.getExplicitWait());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}