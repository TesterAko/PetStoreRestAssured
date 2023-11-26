package PetStore.Store;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

public class StoreService {

    public ValidatableResponse postOrder(String body, int expectedStatus) {
        return spec()
                .body(body)
                .when().post()
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse getOrder(int id, int expectedStatus) {
        return spec()
                .when().get("/{id}", id)
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse deleteOrder(int id, int expectedStatus) {
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
