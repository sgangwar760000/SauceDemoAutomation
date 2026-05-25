package utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    // ✅ Singleton instance
    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {

        // ✅ NEW FOLDER (NOT test-output)
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";

        // ✅ CREATE reports FOLDER IF NOT EXISTS
        File reportFile = new File(reportPath);
        reportFile.getParentFile().mkdirs();

        System.out.println(">>> REPORT PATH: " + reportPath);

        // ✅ INITIALIZE REPORTER
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Automation Test Results");
        reporter.config().setDocumentTitle("Test Execution Report");

        // ✅ ATTACH REPORTER
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Shashikant Gangwar");
        extent.setSystemInfo("Environment", "QA");

        return extent;
    }

    // ✅ FLUSH METHOD
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println(">>> FLUSH EXECUTED <<<");
        } else {
            System.out.println(">>> EXTENT IS NULL <<<");
        }
    }
}