package ru.geekbrains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserProjectControllerTests {

    @Value("${local.server.port}")
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testGetUsersByProjectId() {
        // Замените 1 на реальный ID проекта для тестирования
        when().get("/api/projects/1/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetProjectsByUserId() {
        // Замените 1 на реальный ID пользователя для тестирования
        when().get("/api/projects/users/1/projects")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAddUserToProject() {
        // Замените 1 и 2 на реальные ID пользователя и проекта для тестирования
        given()
                .pathParam("projectId", 1)
                .pathParam("userId", 2)
                .when()
                .post("/api/projects/{projectId}/users/{userId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void testRemoveUserFromProject() {
        // Замените 1 и 2 на реальные ID пользователя и проекта для тестирования
        given()
                .pathParam("projectId", 1)
                .pathParam("userId", 2)
                .when()
                .delete("/api/projects/{projectId}/users/{userId}")
                .then()
                .statusCode(200);
    }
}
