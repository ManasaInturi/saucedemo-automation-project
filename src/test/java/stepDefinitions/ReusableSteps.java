package stepDefinitions;

import Utils.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;

import java.util.HashMap;

import static Utils.BaseTest.getDriver;

public class ReusableSteps {
    WebDriver driver;
    LoginPage loginPage;
    HashMap<String, String> input;
    // This class can be used to define reusable steps that can be shared across different step definition classes.

    public ReusableSteps() {
        // Constructor
        this.driver = getDriver(); // Reuse ThreadLocal WebDriver from BaseTest
        this.loginPage = new LoginPage(getDriver());
    }

    @Given("User is on the login page with credentials")
    public void user_is_on_login_page_with_credentials() {
        driver.getCurrentUrl().contains("saucedemo");
    }

    @When("User enters username and password")
    public void user_enters_username_and_password() {
        this.input = new HashMap<>(ScenarioContext.getAll());
        String username = ScenarioContext.get("username");
        String password = ScenarioContext.get("password");
        loginPage.LoginApplication(username, password);
    }

    @Given("User lands on the product page")
    public void user_lands_on_product_page() {
        driver.getTitle().equalsIgnoreCase("Swag Labs");
    }
}
