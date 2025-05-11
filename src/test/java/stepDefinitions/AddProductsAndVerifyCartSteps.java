
package stepDefinitions;

import Utils.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.ProductsPage;

import java.util.ArrayList;
import java.util.List;

public class AddProductsAndVerifyCartSteps extends BaseTest {

    private final WebDriver driver;
    private final ProductsPage productsPage;
    private final CartPage cartPage;
    String expectedProductName;
    String actualProductName;
    String expectedProductPrice;
    String actualProductPrice;
    List<String> actualProductNames;
    List<String> actualProductPrices;
    List<String> expectedProductNames;
    List<String> expectedProductPrices;
    private static final Logger logger = LogManager.getLogger(AddProductsAndVerifyCartSteps.class);
    public AddProductsAndVerifyCartSteps() {
        this.driver = BaseTest.getDriver();
        this.productsPage = new ProductsPage(driver);
        this.cartPage = new CartPage(driver);
    }

    @When("User selects a product and adds it to the cart")
    public void userSelectsAProductAndAddsItToTheCart() {
        expectedProductName = productsPage.getFirstProductName();
        expectedProductPrice = productsPage.getFirstProductPrice();
        productsPage.addFirstProductToCart();
        productsPage.clickOnCartIcon();
    }

    @Then("User should see the product in the cart")
    public void userShouldSeeTheProductInTheCart() {
        List<WebElement> cartItems = cartPage.getCartItemsList();
        actualProductName = cartItems.get(0).getText().trim();
        List<WebElement> cartItemsPrice = cartPage.getCartItemsPriceList();
        actualProductPrice = cartItemsPrice.get(0).getText().trim();
        int numberOfItems = cartItems.size();
        Assert.assertEquals(numberOfItems, 1, "Cart does not contain exactly one item.");
    }

    @And("User validates the product name")
    public void userValidatesTheProductName() {
        Assert.assertEquals(actualProductName, expectedProductName, "Product name does not match.");
    }

    @And("User validates the product price")
    public void userValidatesTheProductPrice() {
        Assert.assertEquals(actualProductPrice, expectedProductPrice, "Product price does not match.");
    }

    @When("User selects multiple products and adds them to the cart")
    public void userSelectsMultipleProductsAndAddsThemToTheCart() {
        productsPage.addMultipleProductsToCart();
        expectedProductNames = productsPage.getSelectedProductNames();
        expectedProductPrices = productsPage.getSelectedProductPrices();
        productsPage.clickOnCartIcon();
    }

    @Then("User should see all selected products in the cart")
    public void userShouldSeeAllSelectedProductsInTheCart() {
        List<WebElement> cartItems = cartPage.getCartItemsList();
        actualProductNames = new ArrayList<>();
        for (WebElement item : cartItems) {
            actualProductNames.add(item.getText());
        }
        List<WebElement> cartItemsPrice = cartPage.getCartItemsPriceList();
        actualProductPrices = new ArrayList<>();
        for (WebElement price : cartItemsPrice) {
            actualProductPrices.add(price.getText());
        }

        int numberOfItems = cartItems.size();
        Assert.assertEquals(numberOfItems, 4, "Cart does not contain exactly four items.");
    }

    @And("User validates each product name")
    public void userValidatesEachProductName() {
        logger.info("Actual Product Names: " + actualProductNames);
        logger.info("Expected Product Names: " + expectedProductNames);
        Assert.assertEquals(actualProductNames, expectedProductNames, "Product names do not match.");
    }

    @And("User validates each product price")
    public void userValidatesEachProductPrice() {
        logger.info("Actual Product Prices: " + actualProductPrices);
        logger.info("Expected Product Prices: " + expectedProductPrices);
        Assert.assertEquals(actualProductPrices, expectedProductPrices, "Product prices do not match.");
    }

    @When("User removes the product from the cart")
    public void userRemovesTheProductFromTheCart() {
        cartPage.removeFirstProductFromCart();
    }

    @Then("User should not see the removed product in the cart")
    public void userShouldNotSeeTheRemovedProductInTheCart() {
        List<WebElement> cartItems = cartPage.getCartItemsList();
        int numberOfItems = cartItems.size();
        Assert.assertEquals(numberOfItems, 0, "Cart does not contain exactly one item.");
    }
}