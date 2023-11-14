package PetStore;

import io.restassured.RestAssured;
import org.apache.http.HttpHeaders;


import static PetStore.JSONHandler.getPetRequestBody;

import static org.hamcrest.Matchers.equalTo;

public class Pet {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private int id;
    private static String name;
    private static String status;

    Pet(int id, String name, String status){
        this.id = id;
        this.name = name;
        this.status = status;
    }
    static void postPet(int id, String name, String status) {
        RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON)
                .body(getPetRequestBody(id, name, status))
                .when().post()
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Al"))
                .body("status", equalTo("available"));
        System.out.println("Pet has been created");
    }

    static void getPet(int id) {
        RestAssured.given()

                .body(getPetRequestBody(id, name, status))
                .when().get("/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Al"))
                .body("status", equalTo("available"));
        System.out.println("Pet was found");
    }

    static void updatePet( int id, String name, String status) {
        RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON)
                .body(getPetRequestBody(id, name, status))
                .when().put()
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Al"))
                .body("status", equalTo("sold"));
        System.out.println("Pet got updated");
    }

    static void deletePet(int id) {
        RestAssured.given()
                .when().delete("/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(null));
        System.out.println("Pet has been deleted");
    }
}
