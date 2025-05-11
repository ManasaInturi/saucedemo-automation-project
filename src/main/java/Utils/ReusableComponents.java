package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReusableComponents {

    WebDriver driver;

    public ReusableComponents(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForWebElementToBeVisible(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("WebElement is null.");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForWebElementToBeClickable(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("WebElement is null.");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
