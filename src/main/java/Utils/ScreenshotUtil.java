package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }
    public static String getScreenshot(String testCaseName) throws IOException {
        WebDriver driver = getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver instance is not set for the current thread.");
        }

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String destDir = "test-output/Screenshots/" + testCaseName + "_" + timestamp + ".png";
        new File(destDir).getParentFile().mkdirs();

        String filePath = destDir;
        File dest = new File(filePath);
        FileUtils.copyFile(src, dest);

        return "Screenshots/" + testCaseName + "_" + timestamp + ".png";
    }
}