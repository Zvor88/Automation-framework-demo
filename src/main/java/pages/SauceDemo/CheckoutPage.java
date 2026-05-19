package pages.SauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;

public class CheckoutPage {
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement confirmationHeader;

    public CheckoutPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public void fillShippingInformation(String first, String last, String zip) {
        WaitUtils.waitForVisibility(firstNameField).sendKeys(first);
        lastNameField.sendKeys(last);
        postalCodeField.sendKeys(zip);
        WaitUtils.waitForClickability(continueButton).click();
    }

    public void clickFinishOrder() {
        WaitUtils.waitForClickability(finishButton).click();
    }

    public String getOrderConfirmationMessage() {
        return WaitUtils.waitForVisibility(confirmationHeader).getText();
    }
}