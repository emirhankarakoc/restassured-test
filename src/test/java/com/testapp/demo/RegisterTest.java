package com.testapp.demo;


import com.testapp.demo.users.models.CreateUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.testapp.demo.DemoApplicationTests.content_type_json;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("emirhan")

//1- uygulamanin kendisini classes ile verecegiz , webEnvironment = definedport verecegiz ki ayni portta calissin ve istek atsin.
//application-emirhan yaptigimiza gore , ismi emirhan oldu. siz ahmet mehmet yapabilirsiniz.
class RegisterTest {
    @Test
    void register200Test(){
        var req = CreateUserRequest.builder().email("example@gmail.com").password("12345678").build();
        //istek atma zamani , given , when ve then kullanacagiz.


        given()
                .header(content_type_json[0],content_type_json[1])
                .body(req)
        .when()
                .post("/users")
        .then()
                .statusCode(200)
                .body("id",notNullValue())
                .body("email",equalTo("example@gmail.com"))
                .body("password",equalTo("12345678"));



    }

    @Test
    void register400Test(){
        var req =CreateUserRequest.builder().email("alreadyRegistered@gmail.com").password("12345678").build();
        //istek atma zamani , given , when ve then kullanacagiz.


        given()
                .header(content_type_json[0],content_type_json[1])
                .body(req)
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .body("id",notNullValue())
                .body("email",equalTo("alreadyRegistered@gmail.com"))
                .body("password",equalTo("12345678"));

         req =CreateUserRequest.builder().email("alreadyRegistered@gmail.com").password("12345678").build();
        //istek atma zamani , given , when ve then kullanacagiz.


        given()
                .header(content_type_json[0],content_type_json[1])
                .body(req)
        .when()
                .post("/users")
        .then()
                .statusCode(400);


    }










}
