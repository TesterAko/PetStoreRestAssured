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
            "username": "%s",
            "firstName": "%s",
            "lastName": "%s",
            "email": "%s",
            "password": "%s",
            "phone": "string",
            "userStatus": 0
            }
            """, id, username, firstname, lastname, email, password);

    String newBody = String.format("""
            {
            "id": %s,
            "username": "%s",
            "firstName": "%s",
            "lastName": "%s",
            "email": "%s",
            "password": "%s"
            }
            """, id, username + "Muster2", firstname, lastname, email, password);

    @Test
    void testUserLifecyclePositive() {
        System.out.println("Positive Tests");
        userService.createUser(body, HttpStatus.SC_OK)
                .body("code", equalTo(HttpStatus.SC_OK))
                .body("message", equalTo(String.valueOf(id)));
        System.out.println("User has been Created " + body);

        userService.getUser(username, HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("username", equalTo(username))
                .body("firstName", equalTo(firstname))
                .body("lastName", equalTo(lastname))
                .body("email", equalTo(email))
                .body("password", equalTo(password));
        System.out.println("User has been retrieved " + body);


        userService.updateUser(username, newBody, HttpStatus.SC_OK)
                .body("code", equalTo(HttpStatus.SC_OK))
                .body("message", equalTo(String.valueOf(id)));
        System.out.println("User has been updated " + newBody);

        String updatedUsername = username + "Muster2";

        userService.logInUser(updatedUsername, password, HttpStatus.SC_OK)
                .body("code", equalTo(HttpStatus.SC_OK));
        System.out.println("\nUser " + updatedUsername + " has been logged in");

        userService.logOutUser(HttpStatus.SC_OK)
                .body("code", equalTo(HttpStatus.SC_OK))
                .body("message", equalTo("ok"));
        System.out.println("\nUser " + updatedUsername + " has been logged out");

        userService.deleteUser(updatedUsername, HttpStatus.SC_OK)
                .body("code", equalTo(HttpStatus.SC_OK))
                .body("message", equalTo(updatedUsername));
        System.out.println("\nUser has been deleted " + updatedUsername);

    }

    @Test
    void testUserLifecycleNegative() {
        System.out.println("Negative Tests");
        userService.createUser(username, HttpStatus.SC_BAD_REQUEST)
                .body("code", equalTo(HttpStatus.SC_BAD_REQUEST))
                .body("message", equalTo("bad input"));
        System.out.println("User could not be created");

        userService.getUser(username, HttpStatus.SC_NOT_FOUND)
                .body("code", equalTo(1))
                .body("message", equalTo("User not found"));
        System.out.println("User could not be found");

        userService.updateUser("", body, HttpStatus.SC_METHOD_NOT_ALLOWED)
                .statusCode(equalTo(HttpStatus.SC_METHOD_NOT_ALLOWED));
        System.out.println("User could not be updated");


        userService.deleteUser("Test", HttpStatus.SC_NOT_FOUND)
                .statusCode(equalTo(HttpStatus.SC_NOT_FOUND));
        System.out.println("User could not be deleted");
    }
}

