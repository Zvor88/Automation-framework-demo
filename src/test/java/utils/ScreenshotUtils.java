package utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "./target/screenshots/";

    /**
     * Captures a screenshot, saves it to disk with a unique thread-safe timestamp,
     * and returns the raw bytes for report streaming.
     */
    public static byte[] captureScreenshot(String testName) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            System.err.println("⚠️ Cannot capture screenshot: WebDriver context is null.");
            return new byte[0];
        }

        // 1. Generate unique identifier via timestamp and thread identifier mapping
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        long threadId = Thread.currentThread().getId();
        String filename = testName + "_" + timestamp + "_Thread" + threadId + ".png";

        // 2. Capture the screen via Selenium
        TakesScreenshot camera = (TakesScreenshot) driver;
        byte[] screenshotBytes = camera.getScreenshotAs(OutputType.BYTES);
        File srcFile = camera.getScreenshotAs(OutputType.FILE);
        File destFile = new File(SCREENSHOT_DIR + filename);

        // 3. Save a physical copy to the disk for archival history logs
        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("📸 Local screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("⚠️ Failed to write screenshot image copy to disk storage: " + e.getMessage());
        }

        return screenshotBytes;
    }
}