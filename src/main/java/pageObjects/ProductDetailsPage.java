package pageObjects;

import Utils.ReusableComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    private final ReusableComponents reusableComponents;

    public ProductDetailsPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.reusableComponents = new ReusableComponents(driver);
    }

    //WebElements on the products page
    @FindBy(xpath="(//button[normalize-space()='Back to products'])[1]")
    WebElement backToProductsButton;
    @FindBy(xpath="(//div[@class='inventory_details_name large_size'])[1]")
    WebElement productNameOnDetailsPage;
    @FindBy(xpath = "(//div[@class='inventory_details_desc large_size'])[1]")
    WebElement productDescriptionOnDetailsPage;
    @FindBy(xpath = "(//div[@class='inventory_details_price'])[1]")
    WebElement productPriceOnDetailsPage;

    public void waitForProductDetailsPageToOpen() {
        reusableComponents.waitForWebElementToBeVisible(backToProductsButton);
    }

    public String getProductNameOnDetailsPage() {
        reusableComponents.waitForWebElementToBeVisible(productNameOnDetailsPage);
        return productNameOnDetailsPage.getText();
    }

    public String getProductDescriptionOnDetailsPage() {
        reusableComponents.waitForWebElementToBeVisible(productDescriptionOnDetailsPage);
        return productDescriptionOnDetailsPage.getText();
    }

    public String getProductPriceOnDetailsPage() {
        reusableComponents.waitForWebElementToBeVisible(productPriceOnDetailsPage);
        return productPriceOnDetailsPage.getText();
    }
}
