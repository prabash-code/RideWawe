package edu.icet.controller;

import edu.icet.model.dto.request.BookingRequest;
import edu.icet.model.dto.response.BookingResponse;
import edu.icet.model.entity.CarStatus;
import edu.icet.service.BookingServices;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
  private final  BookingServices bookingServices;

  //create new booking
  @PostMapping
    public BookingResponse createBooking(@RequestBody BookingRequest booking){
      return bookingServices.createNewBooking(booking);
    }

//Get all bookings
    @GetMapping
    public List<BookingResponse> getAllBookings(){
      return bookingServices.getAllBookings();
    }

    //get bookings by id

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id){
      return bookingServices.getBookingById(id);
    }

    //update bookings status

    @PatchMapping("/{id}")
    public BookingResponse updateBooking(@PathVariable Long id, @RequestParam CarStatus status){
      return bookingServices.updateBooking(id,status);
    }

    //delete bookings
    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id){
      bookingServices.cancelBooking(id);
    }


}
