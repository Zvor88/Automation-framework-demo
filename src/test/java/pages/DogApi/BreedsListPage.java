package pages.DogApi;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;

public class BreedsListPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(),'Breeds list')]")
    private WebElement breedsHeading;

    @FindBy(css = "button.get-dog")
    private WebElement fetchDogButton;

    @FindBy(css = "div.dog-image img")
    private WebElement dogImage;

    public BreedsListPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public String getHeadingText() {
        return WaitUtils.waitForVisibility(breedsHeading).getText();
    }

    public void clickFetchDogButton() {
        WaitUtils.waitForClickability(fetchDogButton).click();
    }

    public boolean isDogImageDisplayed() {
        return WaitUtils.waitForVisibility(dogImage).isDisplayed();
    }

    public String getDogImageSrc() {
        return WaitUtils.waitForVisibility(dogImage).getAttribute("src");
    }
}