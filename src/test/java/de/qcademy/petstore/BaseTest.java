package de.qcademy.petstore;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

public class BaseTest {
    protected static final String BASE_URI = "https://petstore.swagger.io/v2/pet";

    protected final RequestSpecification requestSpec() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }
}
