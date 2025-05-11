package stepDefinitions;

import Utils.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

public class UserLogoutSteps extends BaseTest {

    private final WebDriver driver;
    private final ProductsPage productsPage;

    public UserLogoutSteps() {
        this.driver = BaseTest.getDriver();
        this.productsPage = new ProductsPage(driver);
    }

    @When("User clicks on the logout button")
    public void userClicksOnTheLogoutButton() {
        productsPage.clickOnMenu();
        productsPage.clickOnLogout();
    }

    @Then("Validate whether the user is logged out successfully")
    public void validateUserLoggedOutSuccessfully() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("saucedemo"), "User is not logged out successfully.");
    }

    @Then("Validate whether the user is redirected to the login page")
    public void validateUserRedirectedToLoginPage() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Swag Labs", "Login page is not displayed correctly.");
    }

    @Then("Validate whether the login page is displayed correctly")
    public void validateLoginPageDisplayedCorrectly() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.checkLoginPageWebElements();
    }
}