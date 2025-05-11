package stepDefinitions;

import Utils.BaseTest;
import Utils.ErrorTextsUtil;
import Utils.GlobalConfigUtil;
import Utils.ScenarioContext;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pageObjects.LoginPage;

import java.util.HashMap;

import static Utils.BaseTest.getDriver;

public class UserLoginSteps {

    WebDriver driver;
    SoftAssert softAssert;
    LoginPage loginPage;
    HashMap<String, String> input;
    public UserLoginSteps() {
        this.driver = BaseTest.getDriver(); // Reuse ThreadLocal WebDriver from BaseTest
        this.loginPage = new LoginPage(getDriver());
        this.softAssert = new SoftAssert();
        this.input = new HashMap<>(ScenarioContext.getAll());
    }


    @Then("Validate whether the user is logged in successfully")
    public void validate_whether_the_user_is_logged_in_successfully() {
        if (input.get("expectedResult").equalsIgnoreCase("valid")) {
            //validate successful login by verifying the URL
            String actualTitle = getDriver().getCurrentUrl();
            String expectedTitle = GlobalConfigUtil.getProperty("navigatedUrl");
            softAssert.assertEquals(actualTitle, expectedTitle);
        }
        softAssert.assertAll();
    }

    @Then("Validate that an invalid login error is displayed")
    public void validateInvalidLoginErrorDisplayed() {
        if (input.get("expectedResult").equalsIgnoreCase("invalid")) {
            //validate error message with invalid username and password
            String actualErrorMsg = loginPage.getErrorMessage();
            String expectedErrorMsg = ErrorTextsUtil.getErrorText("invalidCredentialsError");
            softAssert.assertEquals(actualErrorMsg, expectedErrorMsg);
        }
        softAssert.assertAll();
    }

    @Then("Validate that a locked-out error is displayed")
    public void validateLockedOutErrorDisplayed() {
        if (input.get("expectedResult").equalsIgnoreCase("lockedOutUser")) {
            //validate error message with locked-out user credentials
            String actualErrorMsg = loginPage.getErrorMessage();
            String expectedErrorMsg = ErrorTextsUtil.getErrorText("lockedOutUserError");
            softAssert.assertEquals(actualErrorMsg, expectedErrorMsg);
        }
        softAssert.assertAll();
    }

    @Then("Validate that the username required error is displayed")
    public void validateUsernameRequiredErrorDisplayed() {
        if (input.get("expectedResult").equalsIgnoreCase("nullUsername")) {
            //validate error message with empty username
            String actualErrorMsg = loginPage.getErrorMessage();
            //Read error texts from properties file
            String expectedErrorMsg = ErrorTextsUtil.getErrorText("nullUsernameError");
            softAssert.assertEquals(actualErrorMsg, expectedErrorMsg);
        }
        softAssert.assertAll();
    }

    @Then("Validate that the password required error is displayed")
    public void validatePasswordRequiredErrorDisplayed() {
        if (input.get("expectedResult").equalsIgnoreCase("nullPassword")) {
            //validate error message with empty password
            String actualErrorMsg = loginPage.getErrorMessage();
            String expectedErrorMsg = ErrorTextsUtil.getErrorText("nullPasswordError");
            softAssert.assertEquals(actualErrorMsg, expectedErrorMsg);
        }
        softAssert.assertAll();
    }
}