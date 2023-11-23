package PetStore.User;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

public class UserService {

    public ValidatableResponse createUser(String body, int expectedStatus) {
        return spec()
                .body(body)
                .when().post()
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse getUser(String username, int expectedStatus) {
        return spec()
                .when().get()
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse updateUser(String username, int expectedStatus) {
        return spec()
                .when().put()
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse deleteUser(String username, int expectedStatus) {
        return spec()
                .when().delete()
                .then()
                .statusCode(expectedStatus);

    }

    public ValidatableResponse loginUser(String username, String password, int expectedStatus) {
        return spec()
                .when().get()
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse logoutUser(int expectedStatus) {
        return spec()
                .when().get()
                .then()
                .statusCode(expectedStatus);
    }

    private RequestSpecification spec() {
        return RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }
}
