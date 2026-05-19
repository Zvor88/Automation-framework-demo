package pages.DogApi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutPage {
    private WebDriver driver;

    // Locators mapping via PageFactory
    @FindBy(xpath = "//h1[contains(text(),'About')]")
    private WebElement aboutHeading;

    @FindBy(linkText = "Documentation")
    private WebElement documentationLink;

    @FindBy(linkText = "Breeds list")
    private WebElement breedsListLink;

    public AboutPage(WebDriver driver) {
        this.driver = driver;
        // Initializes WebElements declared using @FindBy
        PageFactory.initElements(driver, this);
    }

    public String getHeadingText() {
        return aboutHeading.getText();
    }

    public DocumentationPage clickDocumentation() {
        documentationLink.click();
        return new DocumentationPage(driver);
    }

    public BreedsListPage clickBreedsList() {
        breedsListLink.click();
        return new BreedsListPage(driver);
    }

}
