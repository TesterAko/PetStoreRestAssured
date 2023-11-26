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
                .when().get("/{username}",username)
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse updateUser(String username, String body, int expectedStatus) {
        return spec()
                .body(body)
                .when().put("/{username}",username)
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse deleteUser(String username, int expectedStatus) {
        return spec()
                .when().delete("/{username}",username)
                .then()
                .statusCode(expectedStatus);

    }

    public ValidatableResponse logInUser(String username, String password, int expectedStatus) {
        String logInUri=  "/login?username=" + username + "&password=" + password;
        return spec()
                .when().get(logInUri)
                .then()
                .statusCode(expectedStatus);
    }

    public ValidatableResponse logOutUser(int expectedStatus) {
        String logOutUri= "/logout";
        return spec()
                .when().get(logOutUri)
                .then()
                .statusCode(expectedStatus);
    }

    private RequestSpecification spec() {
        return RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }
}
