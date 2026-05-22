package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        reporter.config().setReportName("Automation Test Results");
        reporter.config().setDocumentTitle("Test Execution Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Shashikant Gangwar");
        extent.setSystemInfo("Environment", "QA");

        return extent;
    }
}