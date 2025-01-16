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


}
