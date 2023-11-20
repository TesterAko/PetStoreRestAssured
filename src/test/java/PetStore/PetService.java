package PetStore;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;



public class PetService {

    public ValidatableResponse postPet(String body, int expectedStatus) {
        return spec()
                .body(body)
                .when().post()
                .then().statusCode(expectedStatus);
    }

    public ValidatableResponse getPet(int id, int expectedStatus) {
        return spec()
                .when().get("/{id}", id)
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse updatePet(int id, String body, int expectedStatus) {
        return spec()
                .body(body)
                .when().put()
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse deletePet(Integer id, int expectedStatus) {
        RestAssured.defaultParser = Parser.JSON;
        return spec()
                .when().delete("/{id}", id)
                .then()
                .statusCode(expectedStatus);
    }

    private RequestSpecification spec() {
        return RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }
}
