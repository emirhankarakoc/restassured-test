package com.testapp.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.testapp.demo.DemoApplicationTests.content_type_json;
import static io.restassured.RestAssured.given;

@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("emirhan")
//application propertiesin ismini , treden sonra (-) emirhan koyduk. yani ismi emirhan oldu.
class GetAllUsersRequest {


    @Test
    void getAllUsersTest(){
        given()
                .header(content_type_json[0],content_type_json[1])
                .when()
                .get("/users")
                .then().statusCode(200);
    }
}
