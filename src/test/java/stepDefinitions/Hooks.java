package stepDefinitions;

import Utils.*;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hooks {

    private WebDriver driver;

    @Before(order = 0)
    public void setupScenarioContextAndDriver(Scenario scenario) throws Exception {
        // Clear any previous scenario context
        ScenarioContext.clear();

        // Set ScenarioContext items
        ScenarioContext.set("scenarioName", scenario.getName());

        Set<String> tags = new HashSet<>(scenario.getSourceTagNames());
        ScenarioContext.set("tags", String.join(",", tags));

        String featureName = scenario.getUri().getPath();
        if (featureName.contains("/")) {
            featureName = featureName.substring(featureName.lastIndexOf("/") + 1);
        }
        ScenarioContext.set("feature", featureName.replace(".feature", ""));

        // Create ExtentTest and initialize Logger
        ExtentTest test = ExtentReportsManager.getExtentReports().createTest(scenario.getName());
        ExtentReportsManager.setTest(test);

        LoggerUtil.init(Hooks.class);
        LoggerUtil.logInfo("Starting Scenario: " + scenario.getName());

        // Initialize WebDriver and navigate to URL
        driver = new BaseTest().initializeDriver();
        String url = GlobalConfigUtil.getProperty("url");
        LoggerUtil.logInfo("Navigating to URL: " + url);
        driver.get(url);
    }

    @Before(order = 1, value = "@loginData")
    public void loadTestDataFromJson(Scenario scenario) throws IOException {
        List<HashMap<String, String>> dataList =
                new DataReader().getJsonDataToMap("src/test/resources/testdata/LoginData.json");

        for (String tag : scenario.getSourceTagNames()) {
            for (HashMap<String, String> data : dataList) {
                if (data.containsKey("id") && tag.contains(data.get("id"))) {
                    data.forEach(ScenarioContext::set);
                    LoggerUtil.logInfo("Test data loaded for tag: " + tag);
                    return;
                }
            }
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            // Capture and attach screenshot to Cucumber report and Extent
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");

            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            ExtentReportsManager.getTest().fail("Scenario Failed")
                    .addScreenCaptureFromBase64String(base64Screenshot, "Screenshot");

            List<String> logs = LogCaptureAppender.getLogMessages();
            if (!logs.isEmpty()) {
                String formattedLogs = String.join("<br>", logs);
                ExtentReportsManager.getTest().info("<b>Logs:</b><br>" + formattedLogs);
            }

            LogCaptureAppender.clearLogs();
            LoggerUtil.logFail("Scenario failed: " + scenario.getName());
        } else {
            LoggerUtil.logPass("Scenario passed: " + scenario.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
