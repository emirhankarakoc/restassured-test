package com.testapp.demo;

import com.testapp.demo.users.models.UpdateUserRequest;
import com.testapp.demo.users.models.User;
import com.testapp.demo.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.testapp.demo.DemoApplicationTests.content_type_json;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("emirhan")
class UpdateUserTest {

    @Autowired
    private UserRepository userRepository;
//burada construction injection yapmamaliyiz. her birini tek tek autowired deyip enjekte edecegiz.
    @Test
    void updateUserTest200() {
        User user = new User();
        user.setId("testUserId");
        user.setEmail("testemail@gmail.com");
        user.setPassword("123456");
        userRepository.save(user);

        var req = UpdateUserRequest.builder()
                .email("changedemail@mail.com")
                .password("12345")
                .build();

        given()
                .header(content_type_json[0], content_type_json[1])
                .body(req)
                .when()
                .put("/users/testUserId")
                .then()
                .statusCode(200)
                .body("email", equalTo("changedemail@mail.com"))
                .body("password", equalTo("12345"));
    }

    @Test
    void updateUserTest404(){
        var req = UpdateUserRequest.builder()
                .email("randomemail@mail.com")
                .password("12345")
                .build();
        given()
                .header(content_type_json[0], content_type_json[1])
                .body(req)
        .when()
                .put("/users/randomisation")
        .then()
                .statusCode(404);

    }
}
