package tests;

import base.BaseTest1;
import utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemo.CartPage;
import pages.SauceDemo.CheckoutPage;
import pages.SauceDemo.InventoryPage;
import pages.SauceDemo.LoginPage;

public class SauceDemoUI extends BaseTest1 {
    @Test(description = "UI 1: Autentificare cu succes")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("ui.username"), ConfigReader.getProperty("ui.password"));

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products");
    }

    @Test(description = "UI 2: Autentificare eșuată - utilizator blocat")
    public void testLockedOutLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", ConfigReader.getProperty("ui.password"));

        Assert.assertTrue(loginPage.getErrorMessageText().contains("Sorry, this user has been locked out."));
    }

    @Test(description = "UI 3: Adăugare produs în coș din dashboard")
    public void testAddProductToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("ui.username"), ConfigReader.getProperty("ui.password"));

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();

        Assert.assertEquals(inventoryPage.getCartCount(), "1");
    }

    @Test(description = "UI 4: Verificare prezență produs în Cart Page")
    public void testProductVisibilityInCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("ui.username"), ConfigReader.getProperty("ui.password"));

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.getFirstItemName().isEmpty(), "Coșul este gol, produsul nu a fost reținut!");
    }

    @Test(description = "UI 5: Flux complet de la adăugare în coș până la finalizare comandă (Checkout)")
    public void testFullEndToEndCheckoutFlow() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("ui.username"), ConfigReader.getProperty("ui.password"));

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillInformation("Alex", "QA", "123456");
        checkoutPage.clickFinish();

        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Thank you for your order!", "Comanda nu a fost procesată corect!");
    }
}