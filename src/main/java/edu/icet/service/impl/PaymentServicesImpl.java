package edu.icet.service.impl;

import edu.icet.model.dto.request.PaymentRequest;
import edu.icet.model.dto.response.PaymentResponse;
import edu.icet.model.entity.*;
import edu.icet.repository.BookingRepository;
import edu.icet.repository.CarRepository;
import edu.icet.repository.PaymentRepository;
import edu.icet.service.PaymentServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServicesImpl implements PaymentServices  {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;

    //get all payments
    @Override
    public List<PaymentResponse> getAllPayments() {
        List<PaymentResponse> list=new ArrayList<>();
        List<PaymentEntity> all = paymentRepository.findAll();
         
        for(PaymentEntity paymentEntity:all){
            list.add(new PaymentResponse(
                    paymentEntity.getId(),
                    paymentEntity.getBookingId(),
                    paymentEntity.getAmount(),
                    paymentEntity.getType(),
                    paymentEntity.getMethod(),
                    paymentEntity.getPaymentDate()
            ));
        }
        return list;
    }


    @Override
    public PaymentResponse createNewPayment(PaymentRequest payment) {

       BookingEntity booking = bookingRepository.findById(payment.getBookingId()).orElseThrow(()->new RuntimeException("Booking not found"));
       booking.setPaymentStatus(PaymentStatus.PAID);

        PaymentEntity paymentEntity=new PaymentEntity();
        bookingRepository.save(booking);

        CarEntity carEntity = carRepository.findById(booking.getCarId()).orElseThrow(() -> new RuntimeException("Car is not found"));
        carEntity.setStatus(CarStatus.RENTED);
        carRepository.save(carEntity);


        paymentEntity.setBookingId(payment.getBookingId());
        paymentEntity.setMethod(PaymentMethod.valueOf(String.valueOf(payment.getMethod())));
        paymentEntity.setAmount(payment.getAmount());
        paymentEntity.setType(PaymentStatus.PAID);
        paymentEntity.setPaymentDate(LocalDateTime.now());

        paymentRepository.save(paymentEntity);

      return new PaymentResponse(
              paymentEntity.getId(),
              paymentEntity.getBookingId(),
              paymentEntity.getAmount(),
              paymentEntity.getType(),
              paymentEntity.getMethod(),
              paymentEntity.getPaymentDate()
      );
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        PaymentEntity paymentEntity = paymentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No such payment"));

        return new PaymentResponse(
                paymentEntity.getId(),
                paymentEntity.getBookingId(),
                paymentEntity.getAmount(),
                paymentEntity.getType(),
                paymentEntity.getMethod(),
                paymentEntity.getPaymentDate()
        );
    }

    @Override
    public PaymentResponse getPaymentByBookingId(Long bookingId) {
        PaymentEntity paymentEntity=paymentRepository.getPaymentByBookingId(bookingId)
                .orElseThrow(()->new RuntimeException("No matching Booking Id"));

        return new PaymentResponse(
                paymentEntity.getId(),
                paymentEntity.getBookingId(),
                paymentEntity.getAmount(),
                paymentEntity.getType(),
                paymentEntity.getMethod(),
                paymentEntity.getPaymentDate()
        );
    }
}
