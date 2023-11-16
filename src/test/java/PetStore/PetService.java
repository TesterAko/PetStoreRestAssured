package PetStore;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

import static org.hamcrest.Matchers.equalTo;

public class PetService {

    public ValidatableResponse postPet(String body, int expectedStatus) {
        return spec()
                .body(body)
                .when().post()
                .then().statusCode(expectedStatus);
    }

    public void getPet(int id, int expectedStatus) {
        spec()
                .when().get("/{id}", id)
                .then()
                .statusCode(expectedStatus);
        System.out.println("Pet was found");
    }

    public void updatePet(int id, String body, int expectedStatus) {
        spec()
                .body(body)
                .when().put()
                .then()
                .statusCode(expectedStatus);
        System.out.println("Pet got updated");
    }

    public void deletePet(int id, int expectedStatus) {
        RestAssured.given()
                .when().delete("/{id}", id)
                .then()
                .statusCode(expectedStatus)
                .body("id", equalTo(null));
        System.out.println("Pet has been deleted");
    }

    private RequestSpecification spec() {
        return RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }
}
