package com.testapp.demo;

import com.testapp.demo.users.models.UpdateUserRequest;
import com.testapp.demo.users.models.User;
import com.testapp.demo.users.repository.UserRepository;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.testapp.demo.DemoApplicationTests.content_type_json;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("emirhan")
class DeleteUserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void deleteUserTest200() {
        User user = new User();
        user.setId("testUserId");
        user.setEmail("testemail@gmail.com");
        user.setPassword("123456");
        userRepository.save(user);

        given()
                .header(content_type_json[0], content_type_json[1])
                .when()
                .delete("/users/testUserId")
                .then()
                .statusCode(200)
                .body("email", equalTo("testemail@gmail.com"))
                .body("password", equalTo("123456"));
    }

    @Test
    void deleteUserTest404() {
        UpdateUserRequest req = UpdateUserRequest.builder()
                .email("randomemail@mail.com")
                .password("12345")
                .build();

        try {
            given()
                    .header(content_type_json[0], content_type_json[1])
                    .body(req)
                    .when()
                        .delete("/users/randomisation")
                    .then();
        } catch (Exception e) {
            // Assert the response status if an HttpResponseException occurs
            String statusCode = e.getMessage().substring(13, 16); // Extracting status code from error message
            assertEquals("404", statusCode, "Expected a 404 Not Found response for non-existent user deletion");
        }
    }
}
