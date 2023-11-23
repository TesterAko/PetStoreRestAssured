package PetStore.User;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserTest {

    private static final String BASE_URI = "https://petstore.swagger.io/v2/user";

    private final UserService userService;

    public UserTest() {
        this.userService = new UserService();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    int id = 123;
    String username = "Max1";
    String firstname = "Max";
    String lastname = "Mustermann";

    String email = "max.mustermann@hotmail.com";

    String password = "MaxMustermann1";

    String body = String.format("""
            {
              "id": %s,
              "username": %s,
              "firstName": %s,
              "lastName": %s,
              "email": %s,
              "password": %s,
              "phone": "string",
              "userStatus": 0
            }
            """, id, username, firstname, lastname, email, password);

    @Test
    void testUserLifecyclePositive(){
        System.out.println("Positive Tests");
        userService.createUser(body, HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("username", equalTo(username))
                .body("firstName", equalTo(firstname))
                .body("lastName", equalTo(lastname))
                .body("email", equalTo(email))
                .body("password", equalTo(password));
       System.out.println("User has been Created " + body);
    }

}


