package pageObjects;

import Utils.ReusableComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage {

    private final ReusableComponents reusableComponents;

    public ProductsPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.reusableComponents = new ReusableComponents(driver);
    }

    //WebElements on the products page
    @FindBy(xpath="(//select[@class='product_sort_container'])[1]")
    WebElement SortDropdown;
    @FindBy(xpath="(//option[@value='az'])[1]")
    WebElement AtoZOption;
    @FindBy(xpath="(//option[@value='za'])[1]")
    WebElement ZtoAOption;
    @FindBy(xpath="(//option[@value='lohi'])[1]")
    WebElement LowToHighOption;
    @FindBy(xpath="(//option[@value='hilo'])[1]")
    WebElement HighToLowOption;
    @FindBy(xpath = "(//div[normalize-space()='Sauce Labs Backpack'])[1]")
    WebElement backpackProduct;
    @FindBy(xpath = "(//div[@class=\"pricebar\"])[1]/div[1]")
    WebElement backpackProductPrice;
    @FindBy(xpath = "(//div[@class=\"pricebar\"])[2]/div[1]")
    WebElement bikeLightProductPrice;
    @FindBy(xpath = "(//div[@class=\"pricebar\"])[3]/div[1]")
    WebElement boltTShirtProductPrice;
    @FindBy(xpath = "(//div[@class=\"pricebar\"])[4]/div[1]")
    WebElement fleeceJacketProductPrice;
    @FindBy(xpath = "(//div[@class=\"pricebar\"])[5]/div[1]")
    WebElement onesieProductPrice;
    @FindBy(xpath = "(//div[@class=\"pricebar\"])[6]/div[1]")
    WebElement redTShirtProductPrice;
    @FindBy(xpath="(//div[normalize-space()='Sauce Labs Bike Light'])[1]")
    WebElement bikeLightProduct;
    @FindBy(xpath="(//div[normalize-space()='Sauce Labs Bolt T-Shirt'])[1]")
    WebElement boltTShirtProduct;
    @FindBy(xpath="(//div[normalize-space()='Sauce Labs Onesie'])[1]")
    WebElement onesieProduct;
    @FindBy(xpath="(//div[normalize-space()='Test.allTheThings() T-Shirt (Red)'])[1]")
    WebElement redTShirtProduct;
    @FindBy(xpath="(//div[normalize-space()='Sauce Labs Fleece Jacket'])[1]")
    WebElement fleeceJacketProduct;
    @FindBy(xpath="(//div[@class=\"inventory_item_label\"])[1]/div[1]")
    WebElement backpackProductDescription;
    @FindBy(xpath="(//div[@class=\"inventory_item_label\"])[2]/div[1]")
    WebElement bikeLightProductDescription;
    @FindBy(xpath="(//div[@class=\"inventory_item_label\"])[3]/div[1]")
    WebElement boltTShirtProductDescription;
    @FindBy(xpath="(//div[@class=\"inventory_item_label\"])[4]/div[1]")
    WebElement fleeceJacketProductDescription;
    @FindBy(xpath="(//div[@class=\"inventory_item_label\"])[5]/div[1]")
    WebElement onesieProductDescription;
    @FindBy(xpath="(//div[@class=\"inventory_item_label\"])[6]/div[1]")
    WebElement redTShirtProductDescription;
    @FindBy(xpath = "(//button[normalize-space()='Open Menu'])[1]")
    WebElement MenuWebElement;
    @FindBy(xpath="(//a[normalize-space()='Logout'])[1]")
    WebElement logoutLink;
    @FindBy(css = "[class=\"inventory_item_price\"]")
    List<WebElement> priceElements;
    @FindBy(css = "[class=\"inventory_item_name \"]")
    List<WebElement> productNames;
    @FindBy(xpath="(//a[@class='shopping_cart_link'])[1]")
    WebElement cartIcon;
    @FindBy(xpath="(//button[@id='add-to-cart-sauce-labs-backpack'])[1]")
    WebElement addToCartBackpackButton;
    @FindBy(xpath="(//button[@id='add-to-cart-sauce-labs-bike-light'])[1]")
    WebElement addToCartBikeLightButton;
    @FindBy(xpath="(//button[@id='add-to-cart-sauce-labs-bolt-t-shirt'])[1]")
    WebElement addToCartBoltTShirtButton;
    @FindBy(xpath="(//button[@id='add-to-cart-sauce-labs-fleece-jacket'])[1]")
    WebElement addToCartFleeceJacketButton;
    @FindBy(xpath = "//div[@class='inventory_list']/div/div[2]/div[1]/a/div[1]")
    List<WebElement> selectedProductNames;
    @FindBy(xpath="//button[text()='Remove']")
    List<WebElement> removeButtons;

    public void goToBackpackProductDetails() {
        reusableComponents.waitForWebElementToBeVisible(backpackProduct);
        backpackProduct.click();
    }
    public void goToBikeLightProductDetails() {
        reusableComponents.waitForWebElementToBeVisible(bikeLightProduct);
        bikeLightProduct.click();
    }
    public void goToBoltTShirtProductDetails() {
        reusableComponents.waitForWebElementToBeVisible(boltTShirtProduct);
        boltTShirtProduct.click();
    }
    public void goToOnesieProductDetails() {
        reusableComponents.waitForWebElementToBeVisible(onesieProduct);
        onesieProduct.click();
    }
    public void goToRedTShirtProductDetails() {
        reusableComponents.waitForWebElementToBeVisible(redTShirtProduct);
        redTShirtProduct.click();
    }
    public void goToFleeceJacketProductDetails() {
        reusableComponents.waitForWebElementToBeVisible(fleeceJacketProduct);
        fleeceJacketProduct.click();
    }
    public WebElement getDropdownElement() {
        return SortDropdown;
    }
    public void clickOnSortDropdown() {
        reusableComponents.waitForWebElementToBeVisible(SortDropdown);
        SortDropdown.click();
    }
    public List<WebElement> getPriceElements() {
        for (WebElement priceElement : priceElements) {
            reusableComponents.waitForWebElementToBeVisible(priceElement);
        }
        return priceElements;
    }
    public List<WebElement> getNameElements() {
        for (WebElement productName : productNames) {
            reusableComponents.waitForWebElementToBeVisible(productName);
        }
        return productNames;
    }
    public String getBackpackProductPrice() {
        reusableComponents.waitForWebElementToBeVisible(backpackProductPrice);
        return backpackProductPrice.getText();
    }
    public String getBikeLightProductPrice() {
        reusableComponents.waitForWebElementToBeVisible(bikeLightProductPrice);
        return bikeLightProductPrice.getText();
    }
    public String getBoltTShirtProductPrice() {
        reusableComponents.waitForWebElementToBeVisible(boltTShirtProductPrice);
        return boltTShirtProductPrice.getText();
    }
    public String getOnesieProductPrice() {
        reusableComponents.waitForWebElementToBeVisible(onesieProductPrice);
        return onesieProductPrice.getText();
    }
    public String getRedTShirtProductPrice() {
        reusableComponents.waitForWebElementToBeVisible(redTShirtProductPrice);
        return redTShirtProductPrice.getText();
    }
    public String getFleeceJacketProductPrice() {
        reusableComponents.waitForWebElementToBeVisible(fleeceJacketProductPrice);
        return fleeceJacketProductPrice.getText();
    }
    public String getBackpackProductDescription() {
        reusableComponents.waitForWebElementToBeVisible(backpackProductDescription);
        return backpackProductDescription.getText();
    }
    public String getBikeLightProductDescription() {
        reusableComponents.waitForWebElementToBeVisible(bikeLightProductDescription);
        return bikeLightProductDescription.getText();
    }
    public String getBoltTShirtProductDescription() {
        reusableComponents.waitForWebElementToBeVisible(boltTShirtProductDescription);
        return boltTShirtProductDescription.getText();
    }
    public String getOnesieProductDescription() {
        reusableComponents.waitForWebElementToBeVisible(onesieProductDescription);
        return onesieProductDescription.getText();
    }
    public String getRedTShirtProductDescription() {
        reusableComponents.waitForWebElementToBeVisible(redTShirtProductDescription);
        return redTShirtProductDescription.getText();
    }
    public String getFleeceJacketProductDescription() {
        reusableComponents.waitForWebElementToBeVisible(fleeceJacketProductDescription);
        return fleeceJacketProductDescription.getText();
    }
    public void clickOnCartIcon() {
        reusableComponents.waitForWebElementToBeVisible(cartIcon);
        cartIcon.click();
    }
    public String getFirstProductName() {
        reusableComponents.waitForWebElementToBeVisible(backpackProduct);
        return backpackProduct.getText();
    }
    public String getFirstProductPrice(){
        reusableComponents.waitForWebElementToBeVisible(backpackProductPrice);
        return backpackProductPrice.getText();
    }
    public void addFirstProductToCart(){
        reusableComponents.waitForWebElementToBeVisible(addToCartBackpackButton);
        addToCartBackpackButton.click();
    }
    public void addMultipleProductsToCart() {
        reusableComponents.waitForWebElementToBeVisible(addToCartBackpackButton);
        addToCartBackpackButton.click();
        reusableComponents.waitForWebElementToBeVisible(addToCartBikeLightButton);
        addToCartBikeLightButton.click();
        reusableComponents.waitForWebElementToBeVisible(addToCartBoltTShirtButton);
        addToCartBoltTShirtButton.click();
        reusableComponents.waitForWebElementToBeVisible(addToCartFleeceJacketButton);
        addToCartFleeceJacketButton.click();
    }
    public List<String> getSelectedProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement button : removeButtons) {
            // Navigate to the product name within the same inventory item container
            WebElement nameElement = button.findElement(By.xpath("./ancestor::div[contains(@class,'inventory_item')]//div[contains(@class,'inventory_item_name')]"));
            names.add(nameElement.getText().trim());
        }
        return names;
    }

    public List<String> getSelectedProductPrices() {
        List<String> selectedProductPrices = new ArrayList<>();
        for (WebElement button : removeButtons) {
            WebElement priceElement = button.findElement(By.xpath("./ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']"));
            selectedProductPrices.add(priceElement.getText().trim());
        }
        return selectedProductPrices;
    }
    public List<String> getAllProductNames() {
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    public List<String> getAllProductPrices() {
        return priceElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    public void clickOnMenu() {
        reusableComponents.waitForWebElementToBeVisible(MenuWebElement);
        MenuWebElement.click();
    }
    public void clickOnLogout() {
        reusableComponents.waitForWebElementToBeVisible(logoutLink);
        logoutLink.click();
    }
}
