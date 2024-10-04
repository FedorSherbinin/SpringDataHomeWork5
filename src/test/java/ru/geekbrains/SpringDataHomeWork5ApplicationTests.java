package ru.geekbrains;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringDataHomeWork5ApplicationTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testHello() {
        when().get("/")
                .then()
                .statusCode(200)
                .body(is("Hello World!"));
    }

    @Test
    public void testCalc() {
        given().param("left", 100)
                .param("right", 200)
                .when().get("/calc")
                .then()
                .statusCode(200)
                .body("left", is(100))
                .body("right", is(200))
                .body("answer", is(300));
    }
}
