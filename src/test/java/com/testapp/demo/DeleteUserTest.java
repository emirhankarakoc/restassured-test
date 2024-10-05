package com.testapp.demo;

import com.testapp.demo.users.models.UpdateUserRequest;
import com.testapp.demo.users.models.User;
import com.testapp.demo.users.repository.UserRepository;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import static com.testapp.demo.DemoApplicationTests.content_type_json;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("emirhan")
class DeleteUserTest {

    @Autowired
    private UserRepository userRepository;
    //burada construction injection yapmamaliyiz. her birini tek tek autowired deyip enjekte edecegiz.
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
    void deleteUserTest404(){
        var req = UpdateUserRequest.builder()
                .email("randomemail@mail.com")
                .password("12345")
                .build();
       given()
                .header(content_type_json[0], content_type_json[1])
                .body(req)
        .when()
                .delete("/users/randomisation")
        .then()
                .statusCode(404);
    }
}
