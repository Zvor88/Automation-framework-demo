package pages.SauceDemo;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;

public class InventoryPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public String getPageTitleText() {
        return WaitUtils.waitForVisibility(pageTitle).getText();
    }

    public void addBackpackToCart() {
        WaitUtils.waitForClickability(addBackpackButton).click();
    }

    public String getCartBadgeCount() {
        return WaitUtils.waitForVisibility(cartBadge).getText();
    }

    /**
     * Fluent Navigation: Navigates to the Cart review container page
     */
    public CartPage clickCartIcon() {
        WaitUtils.waitForClickability(cartLink).click();
        return new CartPage();
    }
}