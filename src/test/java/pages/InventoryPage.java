package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    private By title = By.className("title");
    private By inventoryItems = By.className("inventory_item");
    private By addToCartButtons = By.cssSelector("button[id^='add-to-cart']");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(title).getText();
    }

    public void addFirstProductToCart() {
        driver.findElements(addToCartButtons).get(0).click();
    }

    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }
}