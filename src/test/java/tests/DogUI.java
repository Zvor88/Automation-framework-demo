package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DogApi.AboutPage;
import pages.DogApi.BreedsListPage;
import pages.DogApi.DocumentationPage;


public class DogUI extends BaseTest {

    @Test(
            priority = 1,
            description = "Sanity Check 01: Verify core elements on the landing About Page."
    )
    public void testAboutPageVitals() {
        AboutPage aboutPage = new AboutPage();

        // Validate page identity and main H1 element heading text
        Assert.assertTrue(getDriver().getTitle().contains("Dog API"), "Tab window title metadata is mismatching.");
        Assert.assertEquals(aboutPage.getHeadingText(), "About", "The primary landing view viewport landmark heading is missing.");
    }

    @Test(
            priority = 2,
            description = "Sanity Check 02: Validate navigation flow to the Documentation area."
    )
    public void testDocumentationPageVitals() {
        AboutPage aboutPage = new AboutPage();

        // Navigate away from About to Docs
        aboutPage.clickDocumentation();
        DocumentationPage docPage = new DocumentationPage();

        // Verify page content loads and the URL updates correctly
        Assert.assertEquals(docPage.getHeadingText(), "Documentation", "Failed to access the API Documentation zone layout.");
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/documentation"), "The current browser window routing path is wrong.");
    }

    @Test(
            priority = 3,
            description = "Sanity Check 03: Navigate back and test dynamic image rendering on Breeds List Page."
    )
    public void testBreedsListPageDynamicVitals() {
        // Return to the entry point base URL to reset navigation state
        AboutPage aboutPage = new AboutPage();

        // Route to the Breeds component view
        aboutPage.clickBreedsList();
        BreedsListPage breedsPage = new BreedsListPage();

        // Verify the heading text matches
        Assert.assertEquals(breedsPage.getHeadingText(), "Breeds list", "Failed to reach the interactive Breeds Listing showcase.");

        // Click the API trigger button
        breedsPage.clickFetchDogButton();

        // WaitUtils handles the network sync automatically before running these assertions
        Assert.assertTrue(breedsPage.isDogImageDisplayed(), "The dynamic dog image element frame failed to load on-screen.");

        String imgPathSource = breedsPage.getDogImageSrc();
        Assert.assertTrue(imgPathSource.startsWith("https://images.dog.ceo/"),
                "The target image source path attribute points to an invalid host location: " + imgPathSource);
    }
}