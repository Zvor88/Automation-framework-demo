package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogApi.AboutPage;
import pages.DogApi.BreedsListPage;
import pages.DogApi.DocumentationPage;


public class DogUI extends BaseTest {

    private static final String BASE_IMAGE_URL = "https://images.dog.ceo/";

    @Test(
            description = "Verify About page is loaded successfully"
    )
    public void shouldLoadAboutPageSuccessfully() {

        AboutPage aboutPage = new AboutPage();

        Assert.assertTrue(
                getDriver().getTitle().contains("Dog API"),
                "Page title does not contain expected text."
        );

        Assert.assertEquals(
                aboutPage.getHeadingText(),
                "ABOUT",
                "About page heading is incorrect."
        );
    }

    @Test(
            description = "Verify navigation to Documentation page"
    )
    public void shouldNavigateToDocumentationPage() {

        AboutPage aboutPage = new AboutPage();

        aboutPage.clickDocumentation();

        DocumentationPage documentationPage = new DocumentationPage();

        Assert.assertEquals(
                documentationPage.getHeadingText(),
                "ENDPOINTS",
                "Documentation page heading is incorrect."
        );

        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("/documentation"),
                "Documentation page URL is incorrect."
        );
    }

    @Test(
            description = "Verify dog image is displayed on Breeds List page"
    )
    public void shouldDisplayDogImageOnBreedsPage() {

        AboutPage aboutPage = new AboutPage();

        aboutPage.clickBreedsList();

        BreedsListPage breedsListPage = new BreedsListPage();

        Assert.assertEquals(
                breedsListPage.getHeadingText(),
                "BREEDS LIST",
                "Breeds List page heading is incorrect."
        );

        breedsListPage.clickFetchDogButton();

        Assert.assertTrue(
                breedsListPage.isDogImageDisplayed(),
                "Dog image is not displayed."
        );

        String imageSource = breedsListPage.getDogImageSrc();

        Assert.assertTrue(
                imageSource.startsWith(BASE_IMAGE_URL),
                "Dog image source URL is invalid: " + imageSource
        );
    }
}