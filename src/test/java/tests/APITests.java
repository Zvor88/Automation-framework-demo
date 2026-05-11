package tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class APITests {

    String baseUri = "https://dogapi.dog/api/v2";

    @Test
    public void testGetUsersList() {
        given()
            .baseUri(baseUri)
        .when()
            .get("/breeds")
        .then()
            .statusCode(200);

    }

}