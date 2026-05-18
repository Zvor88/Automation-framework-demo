package tests;

import utils.ConfigReader;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITests {

    @BeforeClass
    public void setup() {
        // Citim URL-ul din fișierul de configurare optimizat
        baseURI = ConfigReader.getProperty("api.dog.baseUrl");
    }

    @Test(description = "API 1: Listare toate rasele de câini")
    public void testListAllBreeds() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/breeds/list/all")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", notNullValue())
                .body("message.hound", is(notNullValue())); // Verifică dacă rasa 'hound' există în listă
    }

    @Test(description = "API 2: Obținere imagini aleatorii pentru o rasă specifică (Hound)")
    public void testGetBreedImages() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/breed/hound/images")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", hasSize(greaterThan(0))) // Verifică dacă lista de imagini nu este goală
                .body("message[0]", containsString("https://images.dog.ceo/breeds/")); // Verifică structura URL-ului imaginii
    }

    @Test(description = "API 3: Listare sub-rase pentru rasa 'hound'")
    public void testGetSubBreeds() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/breed/hound/list")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", hasItems("afghan", "blood", "english", "ibizan")); // Verifică sub-rasele specifice
    }

    @Test(description = "API 4: Validare eroare pentru o rasă inexistentă (404)")
    public void testBreedNotFound() {
        String rasaInexistenta = "dinozaur";

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/breed/" + rasaInexistenta + "/list")
                .then()
                .statusCode(404) // API-ul returnează 404 pentru rase necunoscute
                .body("status", equalTo("error"))
                .body("message", equalTo("Breed not found (main breed does not exist)"));
    }

    @Test(description = "API Params: Solicită un număr specific de imagini aleatorii folosind parametru în URL")
    public void testGetRandomImagesWithParams() {
        int numberOfImages = 3;

        given()
                .baseUri("https://dog.ceo/api")
                .contentType(ContentType.JSON)
                .pathParam("count", numberOfImages)
                .when()
                .get("/breeds/image/random/{count}")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", hasSize(numberOfImages))
                .body("message[0]", containsString("https://images.dog.ceo/breeds/"));
    }
}