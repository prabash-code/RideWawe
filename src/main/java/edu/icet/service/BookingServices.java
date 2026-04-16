package edu.icet.service;

import edu.icet.model.dto.request.BookingRequest;
import edu.icet.model.dto.response.BookingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingServices {
    BookingResponse createNewBooking(BookingRequest booking,String email);

    List<BookingResponse> getMyBookings(String email);

    List<BookingResponse> getAllBookings();

    BookingResponse getBookingById(Long id);

    BookingResponse updateBooking(Long id, boolean status);

    void cancelBooking(Long id);
}
