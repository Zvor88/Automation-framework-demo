package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final String BADUSER = "Gogu";
    public static final String BADPASSWORD = "Miti";

    public enum Environment {
        DOG_API("https://dog.ceo/dog-api/about"),
        SAUCEDEMO("https://www.saucedemo.com/");

        private final String url;
        Environment(String url) { this.url = url; }
        public String getUrl() { return url; }
    }

    /**
     * Factory pattern with explicit configuration properties
     */
    public static synchronized WebDriver initDriver(String browser) {
        if (tlDriver.get() == null) {
            String targetBrowser = (browser != null) ? browser.toLowerCase().trim() : "chrome";

            switch (targetBrowser) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    tlDriver.set(new FirefoxDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    tlDriver.set(new EdgeDriver());
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-notifications");
                    tlDriver.set(new ChromeDriver(options));
                    break;
            }
        }
        // Apply implicit wait baselines dynamically upon driver build
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public static synchronized void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
