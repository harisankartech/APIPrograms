package GROUPATB;

import io.restassured.RestAssured;

public class APIGetTest {

    public static void main(String[] args) {
        RestAssured.given().baseUri("https://restful-booker.herokuapp.com").basePath("/booking")
                .when().get().then().statusCode(200).log().all();
    }
}
