package edu.icet.controller;

import edu.icet.model.dto.Booking;
import edu.icet.model.entity.CarStatus;
import edu.icet.service.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
  @Autowired
    BookingServices bookingServices;

  //create new booking
  @PostMapping
    public Booking createBooking(@RequestBody Booking booking){
      return bookingServices.createNewBooking(booking);
    }

    //get my bookings
    @GetMapping("/my-bookings")
    public List<Booking> getMyBookings(
            @RequestParam Authentication authentication){
      return bookingServices.getMyBookings(authentication.getName());

    }
    //get all bookings

    @GetMapping
    public List<Booking> getAllBookings(){
      return bookingServices.getAllBookings();
    }

    //get bookings by id

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id){
      return bookingServices.getBookingById(id);
    }

    //update bookings status

    @PatchMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestParam CarStatus status){
      return bookingServices.updateBooking(id,status);
    }

    //delete bookings
    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id){
      bookingServices.cancelBooking(id);
    }


}
