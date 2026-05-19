package tests;

import io.restassured.RestAssured;
import utils.ConfigReader;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DogAPI {

    private static final String ALL_BREEDS_ENDPOINT = "/breeds/list/all";
    private static final String HOUND_IMAGES_ENDPOINT = "/breed/hound/images";
    private static final String HOUND_SUB_BREEDS_ENDPOINT = "/breed/hound/list";
    private static final String RANDOM_IMAGES_ENDPOINT = "/breeds/image/random/{count}";

    private static final String SUCCESS_STATUS = "success";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("api.dog.baseUrl");
    }

    @Test(description = "Verify all dog breeds are returned successfully")
    public void shouldReturnAllDogBreeds() {

        given()
                .contentType(ContentType.JSON)

                .when()
                .get(ALL_BREEDS_ENDPOINT)

                .then()
                .statusCode(200)
                .body("status", equalTo(SUCCESS_STATUS))
                .body("message", notNullValue())
                .body("message.hound", notNullValue());
    }

    @Test(description = "Verify images are returned for Hound breed")
    public void shouldReturnImagesForHoundBreed() {

        given()
                .contentType(ContentType.JSON)

                .when()
                .get(HOUND_IMAGES_ENDPOINT)

                .then()
                .statusCode(200)
                .body("status", equalTo(SUCCESS_STATUS))
                .body("message", not(empty()))
                .body("message[0]", startsWith("https://images.dog.ceo/breeds/"));
    }

    @Test(description = "Verify Hound breed sub-breeds list")
    public void shouldReturnHoundSubBreeds() {

        given()
                .contentType(ContentType.JSON)

                .when()
                .get(HOUND_SUB_BREEDS_ENDPOINT)

                .then()
                .statusCode(200)
                .body("status", equalTo(SUCCESS_STATUS))
                .body("message", containsInAnyOrder(
                        "afghan",
                        "basset",
                        "blood",
                        "english",
                        "ibizan",
                        "plott",
                        "walker"
                ));
    }

    @Test(description = "Verify API returns 404 for invalid breed")
    public void shouldReturn404ForInvalidBreed() {

        String invalidBreed = "dinozaur";

        given()
                .contentType(ContentType.JSON)

                .when()
                .get("/breed/{breed}/list", invalidBreed)

                .then()
                .statusCode(404)
                .body("status", equalTo("error"))
                .body("message",
                        equalTo("Breed not found (main breed does not exist)"));
    }

    @Test(description = "Verify random images endpoint returns requested number of images")
    public void shouldReturnRequestedNumberOfRandomImages() {

        int imageCount = 3;

        given()
                .contentType(ContentType.JSON)
                .pathParam("count", imageCount)

                .when()
                .get(RANDOM_IMAGES_ENDPOINT)

                .then()
                .statusCode(200)
                .body("status", equalTo(SUCCESS_STATUS))
                .body("message", hasSize(imageCount))
                .body("message[0]", startsWith("https://images.dog.ceo/breeds/"));
    }
}