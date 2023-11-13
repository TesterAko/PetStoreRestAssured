import io.restassured.RestAssured;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.equalTo;

public class PetStoreTesting {

    @Test
     void postPet(){
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
    }


    @Test
    void getPet() {
        postPet();
               RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2/pet/22953")
                .when().get()
                .then()
                .statusCode(200)
                .body("id", equalTo(22953))
                .body("name", equalTo("Al"))
                .body("status", equalTo("available"));

        System.out.println("Pet is found");
        System.out.println("Pet id is " + 22953);
        System.out.println("Pet name is " + "Al");
        System.out.println("Pet status is " + "available");

    }

    @Test
    void updatePet() {
        getPet();
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

        System.out.println("Pet is updated");
    }

    @Test
    void deletePet() {
        updatePet();
        RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2/pet/22953")
                .when().delete()
                .then()
                .statusCode(200)
                .body("id", equalTo(null));
        System.out.println("Pet is deleted");
    }
}


