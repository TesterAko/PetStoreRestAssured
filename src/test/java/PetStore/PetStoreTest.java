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


