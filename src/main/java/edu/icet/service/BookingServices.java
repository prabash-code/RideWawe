package edu.icet.service;

import edu.icet.model.dto.Booking;
import edu.icet.model.entity.CarStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingServices {
    Booking createNewBooking(Booking booking);

    List<Booking> getMyBookings(String email);

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    Booking updateBooking(Long id, CarStatus status);

    void cancelBooking(Long id);
}
