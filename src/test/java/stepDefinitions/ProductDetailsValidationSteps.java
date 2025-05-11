package stepDefinitions;

import Utils.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.ProductDetailsPage;
import pageObjects.ProductsPage;

public class ProductDetailsValidationSteps extends BaseTest {

    private final WebDriver driver;
    private final ProductsPage productsPage;
    private final ProductDetailsPage productDetailsPage;
    String expectedProductPrice;
    String expectedProductDescription;

    public ProductDetailsValidationSteps() {
        this.driver = BaseTest.getDriver();
        this.productsPage = new ProductsPage(driver);
        this.productDetailsPage = new ProductDetailsPage(driver);
    }

    @When("User selects a product {string} and navigates to the details page")
    public void userSelectsAProductAndNavigatesToTheDetailsPage(String product) {
        if (product.equalsIgnoreCase("Sauce Labs Backpack")) {
            expectedProductPrice = productsPage.getBackpackProductPrice();
            expectedProductDescription = productsPage.getBackpackProductDescription();
            productsPage.goToBackpackProductDetails();
        } else if (product.equalsIgnoreCase("Sauce Labs Bike Light")) {
            expectedProductPrice = productsPage.getBikeLightProductPrice();
            expectedProductDescription = productsPage.getBikeLightProductDescription();
            productsPage.goToBikeLightProductDetails();
        } else if (product.equalsIgnoreCase("Sauce Labs Bolt T-Shirt")) {
            expectedProductPrice = productsPage.getBoltTShirtProductPrice();
            expectedProductDescription = productsPage.getBoltTShirtProductDescription();
            productsPage.goToBoltTShirtProductDetails();
        } else if (product.equalsIgnoreCase("Sauce Labs Onesie")) {
            expectedProductPrice = productsPage.getOnesieProductPrice();
            expectedProductDescription = productsPage.getOnesieProductDescription();
            productsPage.goToOnesieProductDetails();
        } else if (product.equalsIgnoreCase("Test.allTheThings() T-Shirt (Red)")) {
            expectedProductPrice = productsPage.getRedTShirtProductPrice();
            expectedProductDescription = productsPage.getRedTShirtProductDescription();
            productsPage.goToRedTShirtProductDetails();
        } else if (product.equalsIgnoreCase("Sauce Labs Fleece Jacket")) {
            expectedProductPrice = productsPage.getFleeceJacketProductPrice();
            expectedProductDescription = productsPage.getFleeceJacketProductDescription();
            productsPage.goToFleeceJacketProductDetails();
        }
    }

    @Then("User should see the product details")
    public void userShouldSeeTheProductDetails() {
        productDetailsPage.waitForProductDetailsPageToOpen();
    }

    @And("User validates the product {string} name")
    public void userValidatesTheProductName(String product) {
        String actualProductName = productDetailsPage.getProductNameOnDetailsPage();
        Assert.assertEquals(actualProductName, product, "Product name does not match.");
    }

    @And("User validates the product {string} price")
    public void userValidatesTheProductPrice(String product) {
        String actualProductPrice = productDetailsPage.getProductPriceOnDetailsPage();
        Assert.assertEquals(actualProductPrice, expectedProductPrice, "Product price does not match.");
    }

    @And("User validates the product {string} description")
    public void userValidatesTheProductDescription(String product) {
        String actualProductDescription = productDetailsPage.getProductDescriptionOnDetailsPage();
        Assert.assertEquals(actualProductDescription, expectedProductDescription, "Product description does not match.");
    }
}
