package de.qcademy.petstore;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class PetStoreMikalaiTest extends BaseTest {

    @Test
    void postPetAllFields() {
        int id = 12345;
        String name = "Al";
        String photoUrl = "http://placeimg.com/640/480";
        String status = "available";
        String body = String.format("""
                {
                    "id": %s,
                    "category": {
                        "id": 0,
                        "name": "string"
                    },
                    "name": "%s",
                    "photoUrls": ["%s"],
                    "tags": [
                        {
                            "id": 0,
                            "name": "string"
                        }
                    ],
                    "status": "%s"
                }
                """, id, name, photoUrl, status);

        requestSpec().body(body).when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("photoUrls[0]", equalTo(photoUrl));
    }

    @Test
    void getPetById() {
        int id = 11222333;
        String name = "Al";
        String photoUrl = "http://placeimg.com/640/480";
        String status = "available";
        String body = String.format("""
                {
                    "id": %s,
                    "name": "%s",
                    "photoUrls": ["%s"],
                    "status": "%s"
                }
                """, id, name, photoUrl, status);

        // Create Pet
        requestSpec().body(body).when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("photoUrls[0]", equalTo(photoUrl));

        // Get Pet
        requestSpec().when().get(String.valueOf(id))
                .then().statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("photoUrls[0]", equalTo(photoUrl));
    }

    @Test
    void putPet() {
        int id = 11222333;
        String name = "Al";
        String updatedName = "Al-Updated";
        String photoUrl = "http://placeimg.com/640/480";
        String updatedPhotoUrl = "http://placeimg.com/640/480/updated";
        String status = "available";
        String updatedStatus = "sold";
        String body = String.format("""
                {
                    "id": %s,
                    "name": "%s",
                    "photoUrls": ["%s"],
                    "status": "%s"
                }
                """, id, name, photoUrl, status);

        String bodyForUpdate = String.format("""
                {
                    "id": %s,
                    "name": "%s",
                    "photoUrls": ["%s"],
                    "status": "%s"
                }
                """, id, updatedName, updatedPhotoUrl, updatedStatus);

        // Create Pet
        requestSpec().body(body).when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("photoUrls[0]", equalTo(photoUrl));

        // Update Pet
        requestSpec().body(bodyForUpdate).when().put()
                .then().statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(updatedName))
                .body("status", equalTo(updatedStatus))
                .body("photoUrls[0]", equalTo(updatedPhotoUrl));
    }

    @Test
    void deletePetById() {
        int id = 11222333;
        String name = "Al";
        String photoUrl = "http://placeimg.com/640/480";
        String status = "available";
        String body = String.format("""
                {
                    "id": %s,
                    "name": "%s",
                    "photoUrls": ["%s"],
                    "status": "%s"
                }
                """, id, name, photoUrl, status);

        requestSpec().body(body).when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("photoUrls[0]", equalTo(photoUrl));

        requestSpec().delete(String.valueOf(id)).then().statusCode(HttpStatus.SC_OK);

        requestSpec().body(body).when().get(String.valueOf(id))
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void postPetWrongId() {
        String id = "testBadId";
        String name = "Al";
        String status = "available";
        String body = String.format("""
                {
                    "id": %s,
                    "name": "%s",
                    "status": "%s"
                }
                """, id, name, status);

        requestSpec().body(body).when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void getPetNoId() {
        requestSpec().when().get("000")
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void postPetPhotoUrlsAsStringNotArray() {
        String id = "testBadId";
        String name = "Al";
        String status = "available";
        String photoUrl = "http://placeimg.com/640/480";
        String body = String.format("""
                {
                    "id": %s,
                    "name": "%s",
                    "status": "%s",
                    "photoUrls": "%s"
                }
                """, id, name, status, photoUrl);

        requestSpec().body(body).when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void deleteNotExistingPet() {
        requestSpec().delete("000").then().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
