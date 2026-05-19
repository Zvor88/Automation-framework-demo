package pages.DogApi;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;

public class DocumentationPage {

    @FindBy(xpath = "//h1[contains(text(),'Documentation')]")
    private WebElement docHeading;

    @FindBy(linkText = "All breeds list")
    private WebElement allBreedsApiLink;


    public DocumentationPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public String getHeadingText() {
        return WaitUtils.waitForVisibility(docHeading).getText();
    }

    public void clickAllBreedsApiLink() {
        WaitUtils.waitForClickability(allBreedsApiLink).click();
    }
}