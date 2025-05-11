package pageObjects;

import Utils.ReusableComponents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    private final ReusableComponents reusableComponents;
    private static final Logger logger = LogManager.getLogger(CartPage.class);

    public CartPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.reusableComponents = new ReusableComponents(driver);
    }

    //WebElements on the cart page
    @FindBy(xpath="//div[@class='cart_item']/div[2]/a/div[1]")
    List<WebElement> cartItems;
    @FindBy(xpath = "//div[@class='cart_item']/div[2]/div[2]/div[1]")
    List<WebElement> cartItemsPrice;
    @FindBy(xpath = "//button[contains(@id,'remove')]")
    List<WebElement> removeButtons;

    public List<WebElement> getCartItemsList() {
        if (!cartItems.isEmpty()) {
            reusableComponents.waitForWebElementToBeVisible(cartItems.get(0));
        } else {
            logger.warn("Cart is empty - no items found.");
        }
        return cartItems;
    }
    public List<WebElement> getCartItemsPriceList() {
        if (!cartItemsPrice.isEmpty()) {
            reusableComponents.waitForWebElementToBeVisible(cartItemsPrice.get(0));
        }
        else {
            logger.warn("Cart is empty - no items found.");
        }
        return cartItemsPrice;
    }
    public void removeFirstProductFromCart() {
        if (!removeButtons.isEmpty()) {
            reusableComponents.waitForWebElementToBeVisible(removeButtons.get(0));
            removeButtons.get(0).click();
        }
    }
}
