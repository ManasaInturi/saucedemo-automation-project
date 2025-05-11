package pageObjects;

import Utils.ReusableComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final ReusableComponents reusableComponents;

    public LoginPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.reusableComponents = new ReusableComponents(driver);
    }

    //WebElements on the login page
    @FindBy(id = "user-name")
    WebElement usernameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(id = "login-button")
    WebElement submit;
    @FindBy(xpath = "//div[@class='error-message-container error']")
    WebElement errorMessage;


    public void LoginApplication(String username, String password) {
        reusableComponents.waitForWebElementToBeVisible(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);

        reusableComponents.waitForWebElementToBeVisible(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);

        reusableComponents.waitForWebElementToBeClickable(submit);
        submit.click();
    }

    public String getErrorMessage() {
        reusableComponents.waitForWebElementToBeVisible(errorMessage);
        return errorMessage.getText();
    }

    public void checkLoginPageWebElements(){
        reusableComponents.waitForWebElementToBeVisible(usernameField);
        reusableComponents.waitForWebElementToBeVisible(passwordField);
        reusableComponents.waitForWebElementToBeVisible(submit);
    }
}
