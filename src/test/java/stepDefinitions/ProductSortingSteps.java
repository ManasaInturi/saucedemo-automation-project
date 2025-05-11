package stepDefinitions;

import Utils.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageObjects.ProductsPage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSortingSteps extends BaseTest {

    private final WebDriver driver;
    ProductsPage productsPage;

    public ProductSortingSteps() {
        this.driver = BaseTest.getDriver();
        this.productsPage = new ProductsPage(driver);
    }

    @When("User selects the sort option {string}")
    public void userSelectsTheSortOption(String sortOption) {
        BaseTest baseTest = new BaseTest();
        baseTest.waitForAlertAndAccept();
        productsPage.clickOnSortDropdown();
        WebElement sortDropdown = productsPage.getDropdownElement();
        Select select = new Select(sortDropdown);
        if (sortOption.equalsIgnoreCase("Name: A to Z")) {
            select.selectByIndex(0);
        } else if (sortOption.equalsIgnoreCase("Name: Z to A")) {
            select.selectByIndex(1);
        } else if (sortOption.equalsIgnoreCase("Price: Low to High")) {
            select.selectByIndex(2);
        } else {
            select.selectByIndex(3);
        }
    }

    @Then("Validate whether the products are sorted by price in ascending order")
    public void validateProductsSortedByPriceAscending() {
        List<WebElement> priceElements = productsPage.getPriceElements();
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            actualPrices.add(Double.parseDouble(priceElement.getText().replace("$", "")));
        }
        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        Collections.sort(sortedPrices);

        Assert.assertEquals(actualPrices, sortedPrices, "Products are not sorted by price in ascending order.");
    }

    @Then("Validate whether the products are sorted by price in descending order")
    public void validateProductsSortedByPriceDescending() {
        List<WebElement> priceElements = productsPage.getPriceElements();
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            actualPrices.add(Double.parseDouble(priceElement.getText().replace("$", "")));
        }
        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        sortedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(actualPrices, sortedPrices, "Products are not sorted by price in descending order.");
    }

    @Then("Validate whether the products are sorted by name in alphabetical order")
    public void validateProductsSortedByNameAlphabetical() {
        List<WebElement> nameElements = productsPage.getNameElements();
        List<String> actualNames = new ArrayList<>();
        for (WebElement nameElement : nameElements) {
            actualNames.add(nameElement.getText());
        }
        List<String> sortedNames = new ArrayList<>(actualNames);
        Collections.sort(sortedNames);

        Assert.assertEquals(actualNames, sortedNames, "Products are not sorted by name in alphabetical order.");
    }

    @Then("Validate whether the products are sorted by name in reverse alphabetical order")
    public void validateProductsSortedByNameReverseAlphabetical() {
        List<WebElement> nameElements = productsPage.getNameElements();
        List<String> actualNames = new ArrayList<>();
        for (WebElement nameElement : nameElements) {
            actualNames.add(nameElement.getText());
        }
        List<String> sortedNames = new ArrayList<>(actualNames);
        sortedNames.sort(Collections.reverseOrder());

        Assert.assertEquals(actualNames, sortedNames, "Products are not sorted by name in reverse alphabetical order.");
    }
}