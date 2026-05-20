package pages.SauceDemo;


import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;


public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public boolean isLogoDisplayed() {
        return WaitUtils.waitForVisibility(loginLogo).isDisplayed();
    }

    public InventoryPage loginWithDefaultCredentials() {
        WebElement user = usernameField;
        user.clear();
        user.sendKeys(DriverFactory.USERNAME); // Extragem credidentiale direct din driver factory

        passwordField.clear();
        passwordField.sendKeys(DriverFactory.PASSWORD);

        WaitUtils.waitForClickability(loginButton).click();
        return new InventoryPage();
    }

    public InventoryPage loginWithInvalidCredentials() {

        WebElement user = usernameField;
        user.clear();
        user.sendKeys(DriverFactory.BADUSER); // Extragem credidentiale direct din driver factory

        passwordField.clear();
        passwordField.sendKeys(DriverFactory.BADPASSWORD);

        WaitUtils.waitForClickability(loginButton).click();
        return new InventoryPage();
    }

    public String getErrorMessageText() {
        return WaitUtils.waitForVisibility(errorMessage).getText();
    }
}