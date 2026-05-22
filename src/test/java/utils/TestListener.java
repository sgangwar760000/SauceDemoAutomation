package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Execution Started");
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest = extent.createTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );

        extentTest.assignCategory(result.getTestClass().getName());

        ExtentTestManager.setTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager.getTest().fail(result.getThrowable());

        // ✅ GET DRIVER
        WebDriver driver = DriverFactory.getDriver();

        // ✅ CAPTURE SCREENSHOT
        String path = ScreenshotUtil.capture(
                driver,
                result.getMethod().getMethodName()
        );

        try {
            ExtentTestManager.getTest()
                    .addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Execution Finished");
    }
}