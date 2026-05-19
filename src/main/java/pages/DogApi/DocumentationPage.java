package pages.DogApi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentationPage {
    private WebDriver driver;

    @FindBy(xpath = "//h1[contains(text(),'Documentation')]")
    private WebElement docHeading;

    public DocumentationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeadingText() {
        return docHeading.getText();
    }
}
