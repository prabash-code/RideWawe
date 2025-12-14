package edu.icet.service.impl;

import edu.icet.model.dto.Booking;
import edu.icet.model.dto.User;
import edu.icet.model.entity.BookingEntity;
import edu.icet.model.entity.CarStatus;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.BookingRepository;
import edu.icet.repository.UserRepository;
import edu.icet.service.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServicesImpl implements BookingServices {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Booking createNewBooking(Booking booking) {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setCustomerId(booking.getCustomerId());
        bookingEntity.setCustomerName(booking.getCustomerName());
        bookingEntity.setCustomerEmail(booking.getCustomerEmail());
        bookingEntity.setRegistrationNumber(booking.getRegistrationNumber());
        bookingEntity.setStartDate(booking.getStartDate());
        bookingEntity.setEndDate(booking.getEndDate());
        bookingEntity.setTotalAmount(booking.getTotalAmount());
        bookingEntity.setStatus(booking.getStatus());

        return new Booking(
                bookingEntity.getId(),
                bookingEntity.getCustomerId(),
                bookingEntity.getCustomerName(),
                bookingEntity.getCustomerEmail(),
                bookingEntity.getRegistrationNumber(),
                bookingEntity.getStartDate(),
                bookingEntity.getEndDate(),
                bookingEntity.getTotalAmount(),
                bookingEntity.getStatus(),
                bookingEntity.getCreatedAt(),
                bookingEntity.getUpdatedAt()
        );
    }

    @Override
    public List<Booking> getMyBookings(String email) {
        List<Booking> myList=new ArrayList<>();
        UserEntity userEntity = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Cannot find "));

        Long userId = userEntity.getUserId();

        List<BookingEntity> booksEntity = bookingRepository.findAllById(Collections.singleton(userId));

        for(BookingEntity bookingEntity:booksEntity){
            myList.add(new Booking(
                    bookingEntity.getId(),
                    bookingEntity.getCustomerId(),
                    bookingEntity.getCustomerName(),
                    bookingEntity.getCustomerEmail(),
                    bookingEntity.getRegistrationNumber(),
                    bookingEntity.getStartDate(),
                    bookingEntity.getEndDate(),
                    bookingEntity.getTotalAmount(),
                    bookingEntity.getStatus(),
                    bookingEntity.getCreatedAt(),
                    bookingEntity.getUpdatedAt()
            ));
        }
        return myList;
    }

    @Override
    public List<Booking> getAllBookings() {

        List<Booking> bookingsList = new ArrayList<>();
        List<BookingEntity> all = bookingRepository.findAll();
        for (BookingEntity booking : all) {
            bookingsList.add(new Booking(
                    booking.getId(),
                    booking.getCustomerId(),
                    booking.getCustomerName(),
                    booking.getCustomerEmail(),
                    booking.getRegistrationNumber(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getTotalAmount(),
                    booking.getStatus(),
                    booking.getCreatedAt(),
                    booking.getUpdatedAt()
            ));
        }
        return bookingsList;
    }

    @Override
    public Booking getBookingById(Long id) {
        BookingEntity bookingEntity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));

        return new Booking(
                bookingEntity.getId(),
                bookingEntity.getCustomerId(),
                bookingEntity.getCustomerName(),
                bookingEntity.getCustomerEmail(),
                bookingEntity.getRegistrationNumber(),
                bookingEntity.getStartDate(),
                bookingEntity.getEndDate(),
                bookingEntity.getTotalAmount(),
                bookingEntity.getStatus(),
                bookingEntity.getCreatedAt(),
                bookingEntity.getUpdatedAt()
        );
    }

    @Override
    public Booking updateBooking(Long id, CarStatus status) {
        BookingEntity booking = bookingRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);

        return new Booking(
                booking.getId(),
                booking.getCustomerId(),
                booking.getCustomerName(),
                booking.getCustomerEmail(),
                booking.getRegistrationNumber(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getTotalAmount(),
                booking.getStatus(),
                booking.getCreatedAt(),
                booking.getUpdatedAt()
        );
    }

    @Override
    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
