package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

@CucumberOptions(
        features = {
                "src/test/resources/features/UserLogin.feature",
                "src/test/resources/features/UserLogout.feature",
                "src/test/resources/features/VerifyProductDetailsPostLogin.feature",
        },
        glue = "stepDefinitions",
        plugin = "pretty",
        tags = "@loginData",
        monochrome = true
)
public class LoginLogoutTests extends AbstractTestNGCucumberTests {
        private TestNGCucumberRunner testRunner;

        @BeforeClass(alwaysRun = true)
        public void setUpClass() {
                // Generate timestamp for each run
                String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                String reportPath = "html:target/cucumber-reports/CucumberReports_" + timestamp + ".html";

                // Dynamically set the report path
                System.setProperty("cucumber.plugin", "pretty," + reportPath);

                // Initialize TestNGCucumberRunner
                testRunner = new TestNGCucumberRunner(this.getClass());
        }

        @Test(dataProvider = "scenarios")
        public void runScenario(io.cucumber.testng.PickleWrapper pickle, io.cucumber.testng.FeatureWrapper feature) {
                // Execute the scenario using TestNGCucumberRunner
                testRunner.runScenario(pickle.getPickle());
        }

        @DataProvider
        public Object[][] scenarios() {
                // Provide scenarios dynamically from TestNGCucumberRunner
                return testRunner.provideScenarios();
        }

        @AfterClass(alwaysRun = true)
        public void tearDownClass() {
                testRunner.finish();
        }
}