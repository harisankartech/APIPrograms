package GROUPATB;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class TC_NonBDDStyle {

    static RequestSpecification r = RestAssured.given();

    @Severity(SeverityLevel.BLOCKER)
    @Description("TC1-Positive")
    @Test(priority = 1)
    public void getRequestPOSITIVE(){



        r.baseUri("https://api.zippopotam.us/");
               r.basePath("IN/110002");
                r.when().log().all().get();
                r.then().log().all().statusCode(200);
    }
@Severity(SeverityLevel.NORMAL)
    @Description("TC2-Negative")
    @Test(priority = 2)
    public void RequestNEGATIVE(){

        String pin = "-1";
        r.baseUri("https://api.zippopotam.us/");
        r.basePath("/IN/"+pin);
        r.log().all().when().get();
        r.then().log().all().statusCode(404);

    }
}
