package edu.icet.service.impl;

import edu.icet.model.dto.request.BookingRequest;
import edu.icet.model.dto.response.BookingResponse;
import edu.icet.model.entity.BookingEntity;
import edu.icet.model.entity.CarStatus;
import edu.icet.model.entity.PaymentStatus;
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
    public BookingResponse createNewBooking(BookingRequest booking,String email) {
        BookingEntity bookingEntity = new BookingEntity();

        UserEntity userEntity = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Cannot find "));

        Long userId = userEntity.getUserId();

        bookingEntity.setCarId(booking.getCarId());
        bookingEntity.setCustomerId(userId);
        bookingEntity.setCustomerName(booking.getCustomerName());
        bookingEntity.setCustomerEmail(booking.getCustomerEmail());
        bookingEntity.setRegistrationNumber(booking.getRegistrationNumber());
        bookingEntity.setPickupLocation(booking.getPickupLocation());
        bookingEntity.setFinalLocation(booking.getFinalLocation());
        bookingEntity.setTotalAmount(booking.getTotalAmount());
        bookingEntity.setStartDate(booking.getStartDate());
        bookingEntity.setEndDate(booking.getEndDate());
        bookingEntity.setPaymentStatus(PaymentStatus.PENDING);

        bookingRepository.save(bookingEntity);
        return new BookingResponse(
                bookingEntity.getId(),
                bookingEntity.getCarId(),
                bookingEntity.getCustomerId(),
                bookingEntity.getCustomerName(),
                bookingEntity.getCustomerEmail(),
                bookingEntity.getRegistrationNumber(),
                bookingEntity.getStartDate(),
                bookingEntity.getEndDate(),
                bookingEntity.getTotalAmount(),
                bookingEntity.getCreatedAt(),
                bookingEntity.getUpdatedAt(),
                bookingEntity.getPaymentStatus()

        );

    }


    @Override
    public List<BookingResponse> getMyBookings(String email) {
        List<BookingResponse> myList = new ArrayList<>();
        UserEntity userEntity = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Cannot find "));

        Long userId = userEntity.getUserId();

        List<BookingEntity> booksEntity = bookingRepository.findByCustomerId(userId);

        for (BookingEntity bookingEntity : booksEntity) {
            myList.add(new BookingResponse(
                    bookingEntity.getId(),
                    bookingEntity.getCarId(),
                    bookingEntity.getCustomerId(),
                    bookingEntity.getCustomerName(),
                    bookingEntity.getCustomerEmail(),
                    bookingEntity.getRegistrationNumber(),
                    bookingEntity.getStartDate(),
                    bookingEntity.getEndDate(),
                    bookingEntity.getTotalAmount(),
                    bookingEntity.getCreatedAt(),
                    bookingEntity.getUpdatedAt(),
                    bookingEntity.getPaymentStatus()
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
                    booking.getCarId(),
                    booking.getCustomerId(),
                    booking.getCustomerName(),
                    booking.getCustomerEmail(),
                    booking.getRegistrationNumber(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getTotalAmount(),
                    booking.getCreatedAt(),
                    booking.getUpdatedAt(),
                    booking.getPaymentStatus()

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
                bookingEntity.getCarId(),
                bookingEntity.getCustomerId(),
                bookingEntity.getCustomerName(),
                bookingEntity.getCustomerEmail(),
                bookingEntity.getRegistrationNumber(),
                bookingEntity.getStartDate(),
                bookingEntity.getEndDate(),
                bookingEntity.getTotalAmount(),
                bookingEntity.getCreatedAt(),
                bookingEntity.getUpdatedAt(),
                bookingEntity.getPaymentStatus()
        );
    }

    @Override
    public BookingResponse updateBooking(Long id,boolean success) {
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if(success){
            booking.setPaymentStatus(PaymentStatus.PAID);
        }else{
            booking.setPaymentStatus(PaymentStatus.FAIL);
        }

bookingRepository.save(booking);

        return new BookingResponse(
                booking.getId(),
                booking.getCarId(),
                booking.getCustomerId(),
                booking.getCustomerName(),
                booking.getCustomerEmail(),
                booking.getRegistrationNumber(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getTotalAmount(),
                booking.getCreatedAt(),
                booking.getUpdatedAt(),
                booking.getPaymentStatus()
        );
    }

    @Override
    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
