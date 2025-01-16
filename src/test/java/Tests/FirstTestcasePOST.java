package Tests;

import Assertions.AssertActions;
import BaseTest.BaseTest;
import Endpoints.APIConstants;
import POJOs.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.TestNG;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class FirstTestcasePOST extends BaseTest {

    @Link(name = "JiraTicket",url = "testurl.com")
    @Issue("Issue=01")
    @Description("TC_01-To create a booking")
    @Owner("HarisankarR")
    @Test(priority = 1,groups = {"qa","sanity","prod"})
    public void CreateBookingPOSTRequest(){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.CreatePayloasAsAString()).post();

        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);

        //Default Rest assured-Hamcrest

        validatableResponse.body("booking.firstname", Matchers.equalTo("Hari"));

        //AssertJ

        BookingResponse bookingResponse = payloadManager.bookingResponse(response.asString());

        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Hari");
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotEmpty().isNotBlank();

        //Testng

        assertActions.VerifyStatusCode(response,200);



    }
}
