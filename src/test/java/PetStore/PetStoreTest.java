package PetStore;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class PetStoreTest {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/pet";

    private final PetService petService;

    public PetStoreTest(PetService petService) {
        this.petService = petService;
    }

    @BeforeEach
    void setup() {
        RestAssured.baseURI = BASE_URI;
    }


    @Test
    void testPetLifecyclePositive() {
        System.out.println("Positive Test");

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

        petService.postPet(body, HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("status", equalTo(status))
                .body("photoUrls[0]", equalTo(photoUrl));

        petService.getPet(id, HttpStatus.SC_OK);

        petService.updatePet(id, newBody, HttpStatus.SC_OK);
        petService.deletePet(id, HttpStatus.SC_OK);
    }

    @Test
    void testPetLifecycleNegative() {
        System.out.println("\nNegative Test");
        petService.postPet("", HttpStatus.SC_BAD_REQUEST);

        petService.getPet(123, HttpStatus.SC_NOT_FOUND);

        petService.updatePet(123, "Dog", HttpStatus.SC_BAD_REQUEST);

        petService.deletePet(123, HttpStatus.SC_NOT_FOUND);

    }
}


