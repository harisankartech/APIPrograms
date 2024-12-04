package IntegrationScenario;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC_Integration {
    
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    
   static String token;
   static String booking_id;
    
    @Test
    public String getToken(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);

        response = requestSpecification.when().post();

        validatableResponse = response.then();
        validatableResponse.statusCode(200);

        token = response.jsonPath().getString("token");
        //System.out.println(token);

        return token;





    }

    @Test
    public String getBookingId(){
        String payload = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.log().all();
        requestSpecification.body(payload);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        booking_id = response.jsonPath().getString("bookingid");
        System.out.println("booking id is "+ booking_id);

        return booking_id;

    }

    @Test
    public void TC_1_GetRequest(){
        System.out.println("Hello");
        String booking_id = getBookingId();
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+booking_id);
        requestSpecification.log().all();
        requestSpecification.contentType(ContentType.JSON);

        response = requestSpecification.when().log().all().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

    @Test
    public void TC_2_PostRequest(){

        String payload = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        requestSpecification.log().all();

        response = requestSpecification.when().log().all().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


    }

    @Test
    public void TC_3_PutRequest(){

        String booking_id = getBookingId();
        String payload = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        String token = getToken();
        System.out.println("token is"+token);

        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+booking_id);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payload);
        requestSpecification.log().all();

        response = requestSpecification.log().all().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);





    }

    @Test
    public void TC_4_PatchRequest(){
        String booking_id = getBookingId();
        String payload = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\"\n" +
                "}";

        String token = getToken();
        System.out.println("token is"+token);

        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+booking_id);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payload);
        requestSpecification.log().all();

        response = requestSpecification.log().all().patch();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


    }

    @Test
    public void TC_5_DeleteRequest(){

        booking_id = getBookingId();
        token = getToken();

        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking/"+booking_id);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        response = requestSpecification.when().log().all().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test
    public void TC_1_GetDeletedRequest(){

        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking/"+booking_id);
        requestSpecification.cookie("token",token);

        response = requestSpecification.when().log().all().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
    }



}
