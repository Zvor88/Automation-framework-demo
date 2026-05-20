package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemo.CartPage;
import pages.SauceDemo.CheckoutPage;
import pages.SauceDemo.InventoryPage;
import pages.SauceDemo.LoginPage;


public class SauceDemoUI extends BaseTest {

    @Test(
            priority = 1,
            description = "Sanity Checklist: Verify inventory catalog structure loads post login."
    )
    public void testInventoryDashboardLoads() {
        LoginPage loginPage = new LoginPage();

        // Execute login and capture the fluent return page instance
        InventoryPage inventoryPage = loginPage.loginWithDefaultCredentials();

        Assert.assertEquals(inventoryPage.getPageTitleText(), "Products",
                "The dashboard area catalog landing page title was incorrect.");
    }

    @Test(
            priority = 2,
            description = "E2E Order Cycle: Add item to shopping cart, verify badge, and complete complete checkout flow."
    )
    public void testCompleteE2EPurchasePipeline() {
        LoginPage loginPage = new LoginPage();

        // Fluent Step 1: Login -> Inventory View
        InventoryPage inventoryPage = loginPage.loginWithDefaultCredentials();
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "The item notification badge count is mismatching.");

        // Fluent Step 2: Inventory Icon Click -> Cart Page View
        CartPage cartPage = inventoryPage.clickCartIcon();
        Assert.assertTrue(cartPage.isItemInCart(), "The expected item row was missing inside the shopping cart table canvas.");

        // Fluent Step 3: Cart Checkout -> Form Processing View
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        checkoutPage.fillShippingInformation("John", "Doe", "12345");

        // Fluent Step 4: Submit shipping info and confirm transaction completion
        checkoutPage.clickFinishOrder();
        String successMessage = checkoutPage.getOrderConfirmationMessage();

        Assert.assertEquals(successMessage, "Thank you for your order!",
                "The storefront order fulfillment transaction screen header message was incorrect.");
    }
}


