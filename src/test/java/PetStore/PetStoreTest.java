package PetStore;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class PetStoreTest {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/pet";

    @BeforeEach
    void setup() {
        RestAssured.baseURI = BASE_URI;
    }


    @Test
    void testPetLifecyclePositive() {

        Pet pet = new Pet(12345, "Loco", "available");
        System.out.println("Positive Test");
        pet.postPet();
        pet.getPet();
        pet.updatePet(12345, "Loco", "sold");
        pet.deletePet();
    }



    @Test
    void testPetLifecycleNegative() {

        Pet pet = new Pet(0000, "Non", "Existent");
        System.out.println("\nNegative Test");
        pet.postPetNegative();

        pet.getPetNegative();

        pet.updatePetNegative(0000, "Dog", "sold");

        pet.deletePetNegative();

    }
}






/*
    void postPet() {
        RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2/pet")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body("""
                        {
                            "id": 22953,
                            "category": {
                                "id": 0,
                                "name": "string"
                            },
                            "name": "Al",
                            "photoUrls": [
                                "http://placeimg.com/640/480"
                            ],
                            "tags": [
                                {
                                    "id": 0,
                                    "name": "string"
                                }
                            ],
                            "status": "available"
                        }
                        """)
                .when().post()
                .then()
                .statusCode(200)
                .body("id", equalTo(22953))
                .body("name", equalTo("Al"))
                .body("status", equalTo("available"));
        System.out.println("Pet has been created");
    }

    void getPet() {
        RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2/pet/22953")
                .when().get()
                .then()
                .statusCode(200)
                .body("id", equalTo(22953))
                .body("name", equalTo("Al"))
                .body("status", equalTo("available"));
        System.out.println("Pet was found");
    }

    void updatePet() {
        RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2/pet")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body("""
                        {
                            "id": 22953,
                            "category": {
                                "id": 0,
                                "name": "string"
                            },
                            "name": "Al",
                            "photoUrls": [
                                "http://placeimg.com/640/480"
                            ],
                            "tags": [
                                {
                                    "id": 0,
                                    "name": "string"
                                }
                            ],
                            "status": "sold"
                        }
                        """)
                .when().put()
                .then()
                .statusCode(200)
                .body("id", equalTo(22953))
                .body("name", equalTo("Al"))
                .body("status", equalTo("sold"));
        System.out.println("Pet got updated");
    }

    void deletePet() {
        RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2/pet/22953")
                .when().delete()
                .then()
                .statusCode(200)
                .body("id", equalTo(null));
        System.out.println("Pet has been deleted");
    }
}*/


