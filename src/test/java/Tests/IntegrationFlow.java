package Tests;

import BaseTest.BaseTest;
import Endpoints.APIConstants;
import Modules.PayloadManager;
import POJOs.Booking;
import POJOs.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class IntegrationFlow extends BaseTest {

    @Owner("HarisankarR")
    @Description("TC_1-To create a new booking")
    @Test(priority = 0,groups = {"qa","prod","si"})
    public void TestCreateBooking(ITestContext iTestContext){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.CreatePayloasAsAString()).post();

        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);
        BookingResponse dynamicBookingResponse = payloadManager.bookingResponse(response.asString());

        iTestContext.setAttribute("bookingid",dynamicBookingResponse.getBookingid());



    }
    @Owner("HarisankarR")
    @Description("TC_2-VerifyTheBookingId")
    @Test(priority = 1,groups = {"qa","prod","si"})
    public void TestVerifyBookingId(ITestContext iTestContext){


        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGet = APIConstants.CREATE_UPDATE_BOOKING_URL+ "/" +bookingId;

        requestSpecification.basePath(basePathGet);
        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse = response.then();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank().isNotEmpty();
        assertThat(booking.getFirstname()).isEqualTo("Hari");

    }

    @Owner("HarisankarR")
    @Description("TC_3-Update a booking by id")
    @Test(priority = 2,groups = {"qa","prod","si"})
    public void TestUpdateBookingById(ITestContext iTestContext){
        String token = getToken();
        iTestContext.setAttribute("token",token);
        System.out.println(token);
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPut = APIConstants.CREATE_UPDATE_BOOKING_URL+ "/" +bookingId;

        requestSpecification.basePath(basePathPut);
        response = RestAssured.given(requestSpecification).cookie("token",token).body(payloadManager.FullUpdatePayloadForPut()).log().all().when().put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
    }

    @Owner("HarisankarR")
    @Description("TC_4-Delete a booking by id")
    @Test(priority = 3,groups = {"qa","prod","si"})
    public void TestDeleteBookingById(ITestContext iTestContext){

        String token= (String) iTestContext.getAttribute("token");
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");
        String basePathPut = APIConstants.CREATE_UPDATE_BOOKING_URL+ "/" +bookingId;

        response = RestAssured.given(requestSpecification).cookie("token",token).when().delete();
        validatableResponse = response.then().log().all().statusCode(200);




    }


}
