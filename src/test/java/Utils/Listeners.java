package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Listeners implements ITestListener {

    private static final Logger logger = LogManager.getLogger(Listeners.class);
    private ExtentReports reports;

    @Override
    public void onStart(ITestContext context) {
        reports = ExtentReportsManager.getExtentReports();
        logger.info("Test suite started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Skips the test if the method name is "runScenario" as AbstractTestNGCucumberTests considers it a test method by default
        if (result.getMethod().getMethodName().equals("runScenario")) {
            return;
        }
        String scenarioName = result.getName(); //Cucumber scenario name
        ExtentTest test = reports.createTest(scenarioName);
        ExtentReportsManager.setTest(test);
        try{
            Object feature = ScenarioContext.get("feature");
            // Assign category based on feature
            if (feature != null) {
                test.assignCategory(feature.toString());
            }
            // Assign category based on tags (if available)
            Object tagsObj = ScenarioContext.get("tags");
            if (tagsObj instanceof Set<?>) {
                @SuppressWarnings("unchecked")
                Set<String> tags = (Set<String>) tagsObj;
                for (String tag : tags) {
                    test.assignCategory(tag.replace("@", "")); // Remove '@' prefix
                }
            } else {
                test.assignCategory("Uncategorized");
            }
        } catch (Exception e) {
            test.assignCategory("ContextError");
            logger.error("Error retrieving feature/tags from ScenarioContext: " + e.getMessage());
        }

        logger.info("Test started: " + scenarioName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        if (result.getMethod().getMethodName().equals("runScenario")) {
            return;
        }
        ExtentReportsManager.getTest().pass("Scenario Passed");
        ExtentTest test = ExtentReportsManager.getTest();
        test.log(Status.PASS, "Test Passed");
        addLogsToReport(test);
        logger.info("Test passed: " + result.getName());
        LogCaptureAppender.clearLogs(); // Clear logs after adding them to report
        ExtentReportsManager.removeTest();
        ScenarioContext.clear();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        if (result.getMethod().getMethodName().equals("runScenario")) {
            return;
        }
        ExtentTest test = ExtentReportsManager.getTest();

        try {
            WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
            if (driver != null) {
                ScreenshotUtil.setDriver(driver);
                String filePath = ScreenshotUtil.getScreenshot(result.getName());
                test.fail("Screenshot of failure",
                        MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
            } else {
                test.log(Status.WARNING, "WebDriver instance is null. Screenshot not captured.");
            }
            } catch (IOException e) {
            test.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
            logger.error("Error capturing screenshot: ", e);
        }
        addLogsToReport(test);
        logger.error("Test failed: " + result.getName());
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        LogCaptureAppender.clearLogs();
        ExtentReportsManager.removeTest();
        ScenarioContext.clear();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getMethod().getMethodName().equals("runScenario")) {
            return;
        }
        ExtentTest test = ExtentReportsManager.getTest();
        test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        addLogsToReport(test);
        logger.warn("Test skipped: " + result.getName());
        LogCaptureAppender.clearLogs();
        ExtentReportsManager.removeTest();
        ScenarioContext.clear();
    }

    @Override
    public void onFinish(ITestContext context) {
        if (reports != null) {
            reports.flush();
            try {
                File reportFile = new File(ExtentReportsManager.getReportPath());
                Desktop.getDesktop().browse(reportFile.toURI());
            } catch (IOException e) {
                logger.error("Error opening report file: {}", e.getMessage(), e);
            }
        }
        logger.info("Test suite finished: " + context.getName());
    }

    private void addLogsToReport(ExtentTest test) {
        List<String> logs = LogCaptureAppender.getLogMessages();
        for (String log : logs) {
            test.log(Status.INFO, log);
        }
        test.log(Status.INFO, "Logs added to the report for this test: " + test.getModel().getName());
        LogCaptureAppender.clearLogs();
    }
}