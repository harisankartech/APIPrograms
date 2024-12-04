package GROUPATB;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TC_BDDStyle {


    @Description("TC1-Positive")
    @Test(priority = 1)
    public void getRequestPOSITIVE(){

        RestAssured.given().baseUri("https://api.zippopotam.us/")
                .basePath("IN/110002")
                .when().log().all().get()
                .then().log().all().statusCode(200);
    }

    @Description("TC2-Negative")
    @Test(priority = 2)
    public void RequestNEGATIVE(){

        String pin = "-1";
        RestAssured.given().baseUri("https://api.zippopotam.us/")
                .basePath("IN/"+pin)
                .when().log().all().get()
                .then().log().all().statusCode(404);

    }

    @Description("TC3-Create Token")
    @Test(priority = 3)
    public void test_CreateToken(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";


        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth").contentType(ContentType.JSON).body(payload)
                .when()
                .log().all().post()
                .then().log().all().statusCode(200);
    }



}
