package edu.icet.service.impl;

import edu.icet.model.dto.request.BookingRequest;
import edu.icet.model.dto.response.BookingResponse;
import edu.icet.model.entity.BookingEntity;
import edu.icet.model.entity.CarStatus;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.BookingRepository;
import edu.icet.repository.UserRepository;
import edu.icet.service.BookingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServicesImpl implements BookingServices {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Override
    public BookingResponse createNewBooking(BookingRequest booking) {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setCarId(booking.getCarId());
        bookingEntity.setCustomerId(booking.getCustomerId());
        bookingEntity.setCustomerName(booking.getCustomerName());
        bookingEntity.setCustomerEmail(booking.getCustomerEmail());
        bookingEntity.setRegistrationNumber(booking.getRegistrationNumber());
        bookingEntity.setPickupLocation(booking.getPickupLocation());
        bookingEntity.setFinalLocation(booking.getFinalLocation());
        bookingEntity.setTotalAmount(booking.getTotalAmount());
        bookingEntity.setStartDate(booking.getStartDate());
        bookingEntity.setEndDate(booking.getEndDate());

        bookingRepository.save(bookingEntity);
        return new BookingResponse(
                bookingEntity.getId(),
                bookingEntity.getCustomerId(),
                bookingEntity.getCustomerName(),
                bookingEntity.getCustomerEmail(),
                bookingEntity.getRegistrationNumber(),
                bookingEntity.getStartDate(),
                bookingEntity.getEndDate(),
                bookingEntity.getTotalAmount(),
                bookingEntity.getCreatedAt(),
                bookingEntity.getUpdatedAt()
        );

    }


    @Override
    public List<BookingResponse> getMyBookings(String email) {
        List<BookingResponse> myList = new ArrayList<>();
        UserEntity userEntity = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Cannot find "));

        Long userId = userEntity.getUserId();

        List<BookingEntity> booksEntity = bookingRepository.findAllById(Collections.singleton(userId));

        for (BookingEntity bookingEntity : booksEntity) {
            myList.add(new BookingResponse(
                    bookingEntity.getId(),
                    bookingEntity.getCustomerId(),
                    bookingEntity.getCustomerName(),
                    bookingEntity.getCustomerEmail(),
                    bookingEntity.getRegistrationNumber(),
                    bookingEntity.getStartDate(),
                    bookingEntity.getEndDate(),
                    bookingEntity.getTotalAmount(),
                    bookingEntity.getCreatedAt(),
                    bookingEntity.getUpdatedAt()
            ));
        }
        return myList;
    }

    @Override
    public List<BookingResponse> getAllBookings() {

        List<BookingResponse> bookingsList = new ArrayList<>();
        List<BookingEntity> all = bookingRepository.findAll();
        for (BookingEntity booking : all) {
            bookingsList.add(new BookingResponse(
                    booking.getId(),
                    booking.getCustomerId(),
                    booking.getCustomerName(),
                    booking.getCustomerEmail(),
                    booking.getRegistrationNumber(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getTotalAmount(),
                    booking.getCreatedAt(),
                    booking.getUpdatedAt()
            ));
        }
        return bookingsList;
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        BookingEntity bookingEntity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));

        return new BookingResponse(
                bookingEntity.getId(),
                bookingEntity.getCustomerId(),
                bookingEntity.getCustomerName(),
                bookingEntity.getCustomerEmail(),
                bookingEntity.getRegistrationNumber(),
                bookingEntity.getStartDate(),
                bookingEntity.getEndDate(),
                bookingEntity.getTotalAmount(),
                bookingEntity.getCreatedAt(),
                bookingEntity.getUpdatedAt()
        );
    }

    @Override
    public BookingResponse updateBooking(Long id, CarStatus status) {
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

bookingRepository.save(booking);

        return new BookingResponse(
                booking.getId(),
                booking.getCustomerId(),
                booking.getCustomerName(),
                booking.getCustomerEmail(),
                booking.getRegistrationNumber(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getTotalAmount(),
                booking.getCreatedAt(),
                booking.getUpdatedAt()
        );
    }

    @Override
    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
