package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class UITests extends BaseTest {

    @Test(description = "Test 1: Login succes cu user valid")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("ui.username"), ConfigReader.getProperty("ui.password"));

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Titlul paginii de inventar este incorect!");
    }

    @Test(description = "Test 2: Login eșuat cu user blocat")
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        String error = loginPage.getErrorMessageText();
        Assert.assertTrue(error.contains("Sorry, this user has been locked out"), "Mesajul de eroare nu este cel așteptat!");
    }

    @Test(description = "Test 3: Adăugare produs în coș")
    public void testAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("ui.username"), ConfigReader.getProperty("ui.password"));

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();

        Assert.assertEquals(inventoryPage.getCartCount(), "1", "Numărul de produse din coș nu s-a actualizat!");
    }

    @Test(description = "Test 4: Verificare produs în pagina de coș")
    public void testProductPersistenceInCart() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertFalse(cartPage.getFirstItemName().isEmpty(), "Produsul nu este vizibil în coș!");
    }

    @Test(description = "Test 5: Flux complet de Checkout")
    public void testFullCheckoutFlow() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillInformation("QA", "Tester", "12345");
        checkoutPage.clickFinish();

        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Thank you for your order!", "Comanda nu a fost finalizată cu succes!");
    }
}