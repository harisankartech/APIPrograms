package Modules;

import POJOs.BookingDates;
import POJOs.Booking;
import POJOs.BookingResponse;
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

}
