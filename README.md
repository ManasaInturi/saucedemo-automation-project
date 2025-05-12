# saucedemo-automation-project
This is a robust, cross-platform, data-driven automation framework built using Selenium, Java, TestNG, and Cucumber BDD. The framework follows Page Object Model (POM) and supports parallel execution, logging, rich reporting, and JSON-based test data input.

# Tech Stack
Language: Java
Automation Tool: Selenium WebDriver
Test Frameworks: TestNG & Cucumber & Data Driven
Reporting: ExtentReports, Cucumber HTML
Logging: Log4j2
Build Tool: Maven
Data Format: JSON

# Key Features:
Framework Implementation (Selenium + Java + Cucumber + TestNG)

**Thread-Safe WebDriver:**
Implemented ThreadLocal<WebDriver> to ensure thread safety in parallel executions.

**Screenshot with Timestamp:**
Screenshots taken on test failures are named with date-time stamps to avoid overwriting and help trace failures.

**Timestamped Extent Reports:**
ExtentReports are saved with a unique time-based filename for historical execution tracking.

**Cross-Platform Path Handling:**
All file paths use File.separator to ensure compatibility on Windows, macOS, and Linux.

**ThreadLocal ExtentTest Instance:**
Ensures thread-safe logging and reporting during parallel execution with ThreadLocal<ExtentTest>.

**Log4j2 Logging:**
Centralized logging implemented with Log4j2, attached to ExtentReports for in-depth execution visibility.

**Automatic Screenshot Attachment:**
Failure screenshots are automatically captured and embedded into the ExtentReports.

**Reusable JSON Reader Utility:**
A utility class is provided to fetch and parse test data from JSON dynamically.

**Page Object Model (POM):**
All page elements and actions are encapsulated in dedicated classes, promoting reusability and maintenance.

**Auto-Open Extent Report:**
Report HTML file is automatically launched in the default browser after test execution.

**Execution in full browser and headless mode:**
Cofigurations for cross browser testing in full browser mode and headless mode.

**Parallel execution**
parallel execution of tests through testng.xml

# Cucumber Integration

**Cucumber BDD Structure:**
Implements Feature files, Step Definitions, and Hooks using Cucumber BDD syntax.

**Reusable Step Definitions:**
Commonly used steps are centralized in a ReusableSteps.java class to eliminate redundancy.

**Background Keyword Usage:**
Common preconditions across scenarios in a feature file are defined using the Background keyword.

**Cucumber HTML Report:**
Generates a clean cucumber-html report after execution from the TestRunner class.

**Enhanced tagging for scenario filtering**
Using tags for better scenario grouping or filtering while execution


# Project Structure
src
├── main
│   ├── java
│   │   ├── pageObjects  # POM classes with WebElements and Actions
│   │   ├── utils        # ScreenshotUtil, LoggerUtil, reusableComponents, ExtentReportManager, LogCaptureAppender 
│   ├── resources  		   # log4j2.xml
│		├──
├── test
│   ├── java
│   │   ├── Utils		     #  BaseTest, Listeners, GlobalConfigUtil, ScenarioContext, ErrorTextUtil      
│   │   ├── testRunners  # TestRunner.java for Cucumber execution
│   │   ├── stepDefs     # Cucumber step definitions
│ 	├──resources
│		├──features		           # cucumber feature files
│		├──testdata              # test data JSON files
│		├──global.properties     # global properties
│		├──errorTexts.properties # error texts file
│
├── pom.xml              # mvn dependencies
├── TestNGSuites         # testng.xml
├── log4j2.xml           # for logging
├── extent-config.xml    # Extent report customization
├── target				       # timestamped cucumber html reports
├── test-output			     # timestamped screenshots, timestamped extent reports

# Execution steps
**Cucumber execution:**
Right-click on TestRunner files(SmokeTests.java, LoginLogoutTests.java, FunctionalTests.java) → Run As → Java Application

**TestNG execution**
Right-click on testng.xml → Run As → TestNG Suite
Parallel execution

# Reports
**Extent Reports**
Output: /test-output/extent-report_<timestamp>.html
Includes logs, screenshots, pass/fail status
Auto-opens in browser post-execution

**Cucumber HTML Reports**
Output: /target/cucumber-reports/CucumberReport_<timestamp>.html

# Tools used:

| Tool          | Purpose                           |
| ------------- | --------------------------------- |
| Selenium      | Web UI automation                 |
| Java          | Programming language              |
| Cucumber      | BDD structure & Gherkin syntax    |
| TestNG        | Test runner & data provider       |
| Log4j2        | Logging                           |
| ExtentReports | Reporting with logs & screenshots |
| Maven         | Build & dependency management     |

