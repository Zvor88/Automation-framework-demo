package pages.DogApi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BreedsListPage {
    private WebDriver driver;

    @FindBy(xpath = "//h1[contains(text(),'Breeds list')]")
    private WebElement breedsHeading;

    @FindBy(css = "button.get-dog")
    private WebElement fetchDogButton;

    @FindBy(css = "div.dog-image img")
    private WebElement dogImage;

    public BreedsListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeadingText() {
        return breedsHeading.getText();
    }

    public void clickFetchDogButton() {
        fetchDogButton.click();
    }

    public boolean isDogImageDisplayed() {
        return dogImage.isDisplayed();
    }

    public String getDogImageSrc() {
        return dogImage.getAttribute("src");
    }
}
