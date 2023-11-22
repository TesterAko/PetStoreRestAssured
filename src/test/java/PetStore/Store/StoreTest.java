package PetStore.Store;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.http.HttpStatus;

import static org.hamcrest.CoreMatchers.equalTo;


public class StoreTest {

    private static final String BASE_URI = "https://petstore.swagger.io/v2/store/order/";

    private final StoreService storeService;

    public StoreTest() {
        this.storeService = new StoreService();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    int id = 5;
    int petId = 12345;
    int quantity = 1;
    String body = String.format("""
            {
            "id": %s,
            "petId": %s,
            "quantity": %s,
            "shipDate": "2023-11-22T16:55:21.910",
            "status": "placed",
            "complete": true
            }
            """, id, petId, quantity);

    @Test
    void testOrderLifecyclePositive() {
        System.out.println("Positive Tests");
        storeService.postOrder(body, HttpStatus.SC_OK)
                        .body("id", equalTo(id))
                        .body("petId", equalTo(petId))
                        .body("quantity", equalTo(quantity));
        System.out.println("The order has been placed: "+ body);
        //Problem gelöst, "placed" wurde zweimal deklariert von mir und vom System, deswegen Fehler 400

        storeService.getOrder(id, HttpStatus.SC_OK)
                        .body("id", equalTo(id))
                        .body("petId", equalTo(petId))
                        .body("quantity", equalTo(quantity));
        System.out.println("Order Information: "+ body);


        storeService.deleteOrder(id, HttpStatus.SC_OK)
                        .body("code", equalTo(200))
                        .body("message", equalTo(String.valueOf(id)));
        System.out.println("The order: " + id + " has been deleted.");
    }

    @Test
    void testOrderLifecycleNegative() {
        System.out.println("Negative Tests");
        storeService.postOrder(body, HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("petId", equalTo(petId))
                .body("quantity", equalTo(quantity));
        System.out.println("The order has been placed: "+ body);

        storeService.getOrder(id, HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("petId", equalTo(petId))
                .body("quantity", equalTo(quantity));
        System.out.println("Order Information: "+ body);


        storeService.deleteOrder(id, HttpStatus.SC_OK)
                .body("code", equalTo(200))
                .body("message", equalTo(String.valueOf(id)));
        System.out.println("The order: " + id + " has been deleted.");
    }
}
