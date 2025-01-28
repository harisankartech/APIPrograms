package Modules;

import POJOs.*;
import com.google.gson.Gson;

import java.awt.print.Book;


public class PayloadManager {


    Gson gson;
   public String CreatePayloasAsAString(){

       Booking booking = new Booking();
       booking.setFirstname("Hari");
       booking.setLastname("SankarR");
       booking.setTotalprice(3001);
       booking.setDepositpaid(true);
       BookingDates bookingDates = new BookingDates();
       bookingDates.setCheckin("2025-01-01");
       bookingDates.setCheckout("2025-01-01");
       booking.setBookingdates(bookingDates);
       booking.setAdditionalneeds("Breakfast");

       gson = new Gson();
       String payloadAsString = gson.toJson(booking);

       return  payloadAsString;

   }
   public BookingResponse bookingResponse(String responseString){


       gson = new Gson();
       BookingResponse bookingResponse =gson.fromJson(responseString, BookingResponse.class);

       return bookingResponse;
   }

   public Booking getResponseFromJSON(String response){

       Booking booking = gson.fromJson(response,Booking.class);
       return booking;
   }

   public String SetAuthPayload(){
       Auth authobject = new Auth();
       authobject.setUsername("admin");
       authobject.setPassword("password123");

       gson = new Gson();
       String authAsString = gson.toJson(authobject);
       return authAsString;
   }

   public String GetTokenFromJson(String tokenResponse){

       gson = new Gson();
       TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
       return tokenResponse1.getToken();

   }

    public String FullUpdatePayloadForPut(){

        Booking booking = new Booking();
        booking.setFirstname("Sam");
        booking.setLastname("C Daniel");
        booking.setTotalprice(3001);
        booking.setDepositpaid(true);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-01-01");
        bookingDates.setCheckout("2025-01-01");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        gson = new Gson();
        String payloadForPut = gson.toJson(booking);

        return  payloadForPut;

    }

}
