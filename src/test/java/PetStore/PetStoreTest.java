package PetStore;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.equalTo;


public class PetStoreTest {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/pet";

    private final PetService petService;

    public PetStoreTest() {
        this.petService = new PetService();
    }

    @BeforeEach
    void setup() {
        RestAssured.baseURI = BASE_URI;
    }

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

        String newBody = String.format("""
                {
                    "id": %s,
                    "name": "%s",
                    "photoUrls": ["%s"],
                    "status": "%s"
                }
                """, id, name, photoUrl, status);
    @Test
    void testPetLifecyclePositive() {
        System.out.println("Positive Test");
        petService.postPet(body, HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("photoUrls[0]", equalTo(photoUrl));
        System.out.println("Pet has been created");

        petService.getPet(id, HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("photoUrls[0]", equalTo(photoUrl));
        System.out.println("Pets has been found: " + body);

        petService.updatePet(id, newBody, HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("photoUrls[0]", equalTo(photoUrl));
        System.out.println("Pet got updated: " + newBody);

        petService.deletePet(id, HttpStatus.SC_OK)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(String.valueOf(id)));
        System.out.println("Pet has been deleted");
    }

    @Test
    void testPetLifecycleNegative() {
        System.out.println("\nNegative Test");

        petService.postPet("", HttpStatus.SC_METHOD_NOT_ALLOWED)
                .body("code", equalTo(405))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("no data"));
        System.out.println("Pet could not be created");

        petService.getPet(id, HttpStatus.SC_NOT_FOUND)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("Pet not found"));
        System.out.println("Pet could not be found");

        petService.updatePet(id, "Dog", HttpStatus.SC_BAD_REQUEST)
                .body("code", equalTo(400))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("bad input"));
        System.out.println("Pet could not be updated");


        petService.deletePet(id,HttpStatus.SC_NOT_FOUND)
                .statusCode(equalTo(404));
        System.out.println("Pet could not be deleted");
    }
}


