package pages.DogApi;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;

public class AboutPage extends BasePage {

    // Locators mapping via PageFactory
    @FindBy(xpath = "//h3[contains(text(),'About')]")
    private WebElement aboutHeading;

    @FindBy(linkText = "Documentation")
    private WebElement documentationLink;

    @FindBy(linkText = "Breeds list")
    private WebElement breedsListLink;


    public AboutPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public String getHeadingText() {
        return WaitUtils.waitForVisibility(aboutHeading).getText();
    }


    public void clickDocumentation() {
        WaitUtils.waitForClickability(documentationLink).click();
    }

    public void clickBreedsList() {
        WaitUtils.waitForClickability(breedsListLink).click();
    }
}