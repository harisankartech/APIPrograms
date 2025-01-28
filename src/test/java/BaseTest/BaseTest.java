package BaseTest;

import Assertions.AssertActions;
import Endpoints.APIConstants;
import Modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;


    @BeforeTest
    public void setup(){

        payloadManager =new PayloadManager();
        assertActions = new AssertActions();

        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URl).contentType(ContentType.JSON).log().all();

    }


    public String getToken(){

        payloadManager = new PayloadManager();
        assertActions  = new AssertActions();

        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URl).basePath(APIConstants.AUTHORISATION_URL);

        String payload = payloadManager.SetAuthPayload();;

        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        String token = payloadManager.GetTokenFromJson(response.asString());

        return token;


    }


}
