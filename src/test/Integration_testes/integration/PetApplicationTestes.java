package integration;


import br.com.pet.control.dto.LoginResponseDTO;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.pet.control.dto.AuthenticationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PetApplicationTestes {
    private static LoginResponseDTO loginResponse;
    @Test
    @Order(1)
    public void testSignin() throws JsonMappingException, JsonProcessingException {

        AuthenticationDTO user =
                new AuthenticationDTO("root", "P4o3l8l1@@");

        loginResponse = given()
                .basePath("/auth/login")
                .port(TestsConfig.SERVER_PORT)
                .contentType(TestsConfig.CONTENT_TYPE_JSON)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(LoginResponseDTO.class);

        assertNotNull(user);

    }
}
