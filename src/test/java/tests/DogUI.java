package tests;

import base.BaseTest2;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogApi.AboutPage;
import pages.DogApi.BreedsListPage;
import pages.DogApi.DocumentationPage;

public class DogUI extends BaseTest2 {

    @Test(priority = 1, description = "Sanity Check: Validate About page content and structural headers.")
    public void testAboutPageLandingSanity() {
        AboutPage aboutPage = new AboutPage(driver);

        // Assert page title contains expected metadata text
        Assert.assertTrue(driver.getTitle().contains("Dog API"), "Title does not match.");

        // Assert critical typography headers are visible
        Assert.assertEquals(aboutPage.getHeadingText(), "About", "The principal page heading did not match.");
    }

    @Test(priority = 2, description = "Sanity Check: Validate navigation links forward users to Documentation accurately.")
    public void testNavigationToDocumentation() {
        AboutPage aboutPage = new AboutPage(driver);

        // Run navigation flow action
        DocumentationPage docPage = aboutPage.clickDocumentation();

        // Assert target page specific content elements
        Assert.assertEquals(docPage.getHeadingText(), "Documentation", "Failed to navigate to Documentation view.");
        Assert.assertTrue(driver.getCurrentUrl().contains("/documentation"), "URL does not point to documentation router.");
    }

    @Test(priority = 3, description = "Sanity Check: Test standard functionality on Breeds list page by fetching an image.")
    public void testBreedsListImageGenerationSanity() throws InterruptedException {
        AboutPage aboutPage = new AboutPage(driver);
        BreedsListPage breedsPage = aboutPage.clickBreedsList();

        // Verify we reached the Breeds View
        Assert.assertEquals(breedsPage.getHeadingText(), "Breeds list");

        // Action: Click button to fetch a random dog image dynamically via UI
        breedsPage.clickFetchDogButton();

        // Small pause to allow backend script asset binding to catch up
        Thread.sleep(1500);

        // Assert image container is visible on screen and contains a valid source location
        Assert.assertTrue(breedsPage.isDogImageDisplayed(), "Dog photo canvas failed to display after interactive action.");
        String imageSrc = breedsPage.getDogImageSrc();
        Assert.assertTrue(imageSrc.startsWith("https://images.dog.ceo/"), "Image source property pointing to wrong directory path: " + imageSrc);
    }
}
