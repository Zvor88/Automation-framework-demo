package pages.DogApi;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;

public class DocumentationPage extends BasePage {

    @FindBy(xpath = "//h3[contains(text(),'Endpoints')]")
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