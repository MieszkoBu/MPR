package com.example.Lab2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;

@RestClientTest

public class RestControllerTest {
    private  static final String URI = "http://localhost:8082";
    @Test
    public  void testGet(){
        when()
                .get(URI + "/findbyId/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id",equalTo(1))
                .body("marka",equalTo("a"))
                .log()
                .body();
    }

    @Test
    public void testPost(){
        with()
                .body(new Car("BMW","C",2009))
                .contentType("application/json")
                .post(URI + "/Car/add")
                .then()
                .statusCode(200)
                .assertThat()
                .body("rok_produkcji",equalTo(2009))
                .log()
                .body();
    }
    @Test
    public void testPut(){
        with()
                .body(new Car("BMW","a",1999))
                .contentType("application/json")
                .put(URI + "/car/edit/3")
                .then()
                .statusCode(200)
                .log()
                .body();
    }
    @Test
    public void testDelete(){
        when()
                .delete(URI + "/ucar/4")
                .then()
                .statusCode(200);
    }

}
