package edu.icet.controller;

import edu.icet.model.dto.request.BookingRequest;
import edu.icet.model.dto.response.BookingResponse;
import edu.icet.model.entity.CarStatus;
import edu.icet.service.BookingServices;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {
  private final  BookingServices bookingServices;

  //create new booking
  @PostMapping
  public BookingResponse createBooking(
          @RequestBody BookingRequest booking,
          Authentication authentication) {

      if(authentication == null){
          throw new RuntimeException("User not authenticated");
      }

      String email = authentication.getName();

      return bookingServices.createNewBooking(booking, email);
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

    @GetMapping("/email")
    public List<BookingResponse> getBookingsByEmail(Authentication authentication){
        if(authentication == null){
            System.out.println("AUTH NULL");
            return List.of();
        }

      String email=authentication.getName();
      return bookingServices.getMyBookings(email);
    }

    //update bookings status

    @PutMapping("/{id}/payment")
    public BookingResponse updateBooking(@PathVariable Long id, @RequestParam boolean success){
      return bookingServices.updateBooking(id,success);
    }


    //delete bookings
    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id){
      bookingServices.cancelBooking(id);
    }


}
