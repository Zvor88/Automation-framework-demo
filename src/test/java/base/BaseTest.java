package base;

import listener.TestListener;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

import java.time.Duration;

@Listeners({TestListener.class})
public class BaseTest {

    @BeforeMethod
    @Parameters({"browser", "env"})
    public void setUp(@Optional("chrome") String browser, @Optional("DOG_API") String env) {
        // Step 1: Initialize driver via Factory
        WebDriver driver = DriverFactory.initDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 2: Route dynamically to target environment enum
        DriverFactory.Environment environment = DriverFactory.Environment.valueOf(env.toUpperCase());
        driver.get(environment.getUrl());
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}