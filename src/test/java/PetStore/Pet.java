package PetStore;
/*
import io.restassured.RestAssured;
import org.apache.http.HttpHeaders;


import static PetStore.JSONHandler.getPetRequestBody;

import static org.hamcrest.Matchers.equalTo;

public class Pet {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static int id;
    private static String name;
    private static String status;

    Pet(Integer id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }


    static void postPet() {
        RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON)
                .body(getPetRequestBody(id, name, status))
                .when().post()
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status));
        System.out.println("Pet has been created");
    }

    static void postPetNegative() {
        if (!isValidIdFormat(id)) {
            System.out.println("Pet could not be created");
            return;
        }
        RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON)
                .body(getPetRequestBody(id, name, status))
                .when().post()
                .then()
                .statusCode(404);
    }

    private static boolean isValidIdFormat(Integer id) {
        return id != null && id > 0;
    }


    static void getPet() {
        RestAssured.given()
                .body(getPetRequestBody(id, name, status))
                .when().get("/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status));
        System.out.println("Pet was found");
    }

    static void getPetNegative() {
        RestAssured.given()
                .when().get("/{id}", id)
                .then()
                .statusCode(404);
        System.out.println("Pet could not be found");
    }

    static void updatePet(int id, String name, String status) {
        RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON)
                .body(getPetRequestBody(id, name, status))
                .when().put()
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status));
        System.out.println("Pet got updated");
    }

    static void updatePetNegative(int id, String name, String status) {
        if (!isPetPresent(id)) {
            System.out.println("Pet with ID " + id + " not found. Cannot update.");
            return;
        }
        RestAssured.given()
                .header(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON)
                .body(getPetRequestBody(id, name, status))
                .when().put()
                .then()
                .statusCode(404);
    }

    private static boolean isPetPresent(int id) {
        return petExistsInDatabase(id);
    }

    private static boolean petExistsInDatabase(int id) {
        return id > 0 && id < 1000;
    }




    static void deletePet() {
        RestAssured.given()
                .when().delete("/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(null));
        System.out.println("Pet has been deleted");
    }

    static void deletePetNegative() {
        RestAssured.given()
                .when().delete("/{id}", id)
                .then()
                .statusCode(404);
        System.out.println("Pet could not be deleted");
    }
}
*/