package listener;


import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 [STARTING] Test Method: " + result.getMethod().getMethodName()
                + " on Thread ID: " + Thread.currentThread().getId());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ [PASSED] Test Method: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ [FAILED] Test Method: " + result.getMethod().getMethodName());

        try {
            // Fetch screenshot bytes via our utility and pipe them to Allure
            byte[] screenshot = ScreenshotUtils.captureScreenshot(result.getName());
            if (screenshot.length > 0) {
                attachScreenshotToAllureReport(result.getName() + "_failure_attachment", screenshot);
            }
        } catch (Exception e) {
            System.err.println("⚠️ Interceptor error tracking failure attachment streaming: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭️ [SKIPPED] Test Method: " + result.getMethod().getMethodName());
    }

    /**
     * Streams the captured byte stream directly into the Allure HTML report canvas workspace.
     */
    @Attachment(value = "{attachmentName}", type = "image/png")
    public byte[] attachScreenshotToAllureReport(String attachmentName, byte[] screenshotBytes) {
        return screenshotBytes;
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🏁 [SUITE START] Launching Test Context Suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 [SUITE FINISH] Completed Test Context Suite: " + context.getName());
    }
}
