package stepDefinitions;

import Utils.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.ProductsPage;

import java.util.List;

public class VerifyProductDetailsPostLoginSteps extends BaseTest {

    private final WebDriver driver;
    private final ProductsPage productsPage;
    private List<String> productNames;
    private List<String> productPrices;

    public VerifyProductDetailsPostLoginSteps() {
        this.driver = BaseTest.getDriver();
        this.productsPage = new ProductsPage(driver);
    }

    @When("User fetches all product names and prices")
    public void userFetchesAllProductNamesAndPrices() {
        productNames = productsPage.getAllProductNames();
        productPrices = productsPage.getAllProductPrices();
    }

    @Then("User should see all product names displayed")
    public void userShouldSeeAllProductNamesDisplayed() {
        Assert.assertFalse(productNames.isEmpty(), "Product names list is empty.");
        for (String name : productNames) {
            Assert.assertNotNull(name, "Product name is null.");
            Assert.assertFalse(name.trim().isEmpty(), "Product name is empty.");
        }
    }

    @Then("User should see all product prices displayed")
    public void userShouldSeeAllProductPricesDisplayed() {
        Assert.assertFalse(productPrices.isEmpty(), "Product prices list is empty.");
        for (String price : productPrices) {
            Assert.assertNotNull(price, "Product price is null.");
            Assert.assertFalse(price.trim().isEmpty(), "Product price is empty.");
        }
    }
}