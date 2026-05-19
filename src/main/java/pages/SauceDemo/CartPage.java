package pages.SauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;

public class CartPage {
    @FindBy(className = "cart_item")
    private WebElement cartItemRow;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public boolean isItemInCart() {
        return WaitUtils.waitForVisibility(cartItemRow).isDisplayed();
    }

    /**
     * Fluent Navigation: Advances directly into checkout forms processing
     */
    public CheckoutPage clickCheckout() {
        WaitUtils.waitForClickability(checkoutButton).click();
        return new CheckoutPage();
    }
}