package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    private String url;
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    public WebDriver initializeDriver() throws IOException {

        //get browser name and URL from global properties file
        String browserName = GlobalConfigUtil.getProperty("browser");
        url = GlobalConfigUtil.getProperty("url");

        if(StringUtils.isBlank(browserName)) {
            throw new IllegalArgumentException("Browser name is not specified in the properties file.");
        }
        logger.info("Initializing browser: " + browserName);
        // Initialize WebDriver based on the browser name
        WebDriver driver;
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-extensions");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            options.addArguments("user-data-dir=/tmp/temp_chrome_profile");
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);

        }
        else if (browserName.equalsIgnoreCase("chrome-headless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("firefox-headless")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            driver = new FirefoxDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("edge-headless")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driverThreadLocal.set(driver);
        ScreenshotUtil.setDriver(driver); // Set the driver in ScreenshotUtil
        return driver;
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    //method to run before each test method
    @BeforeMethod(alwaysRun = true)
    public void launchApplication(ITestContext context) throws IOException {
        WebDriver driver = initializeDriver();
        context.setAttribute("WebDriver", driver);
        driver.get(url);
    }

    //method to run after each test method
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
        }
    }

    public void waitForAlertAndAccept() {
        try {
            WebDriver driver = getDriver();
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            logger.error("Alert not present: " + e.getMessage());
        }
    }
}
