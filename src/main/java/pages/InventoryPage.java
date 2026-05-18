package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(css = "button[id^='add-to-cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void addFirstProductToCart() {
        if (!addToCartButtons.isEmpty()) {
            addToCartButtons.get(0).click();
        }
    }

    public String getCartCount() {
        return cartBadge.getText();
    }

    public void goToCart() {
        cartLink.click();
    }
}