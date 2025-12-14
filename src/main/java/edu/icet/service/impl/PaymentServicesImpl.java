package edu.icet.service.impl;

import edu.icet.model.dto.Booking;
import edu.icet.model.dto.Payment;
import edu.icet.model.entity.PaymentEntity;
import edu.icet.repository.PaymentRepository;
import edu.icet.service.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServicesImpl implements PaymentServices  {
    @Autowired
    PaymentRepository paymentRepository;

    //get all payments
    @Override
    public List<Payment> getAllPayments() {
        List<Payment> list=new ArrayList<>();
        List<PaymentEntity> all = paymentRepository.findAll();
         
        for(PaymentEntity paymentEntity:all){
            list.add(new Payment(
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

    //create payment
    @Override
    public Payment createNewPayment(Payment payment) {
      PaymentEntity paymentEntity=new PaymentEntity();

      paymentEntity.setType(payment.getType());
      paymentEntity.setAmount(payment.getAmount());
      paymentEntity.setMethod(payment.getMethod());
      paymentEntity.setBookingId(payment.getBookingId());

      return new Payment(
              paymentEntity.getId(),
              paymentEntity.getBookingId(),
              paymentEntity.getAmount(),
              paymentEntity.getType(),
              paymentEntity.getMethod(),
              paymentEntity.getPaymentDate()
      );
    }

    @Override
    public Payment getPaymentById(Long id) {
        PaymentEntity paymentEntity = paymentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No such payment"));

        return new Payment(
                paymentEntity.getId(),
                paymentEntity.getBookingId(),
                paymentEntity.getAmount(),
                paymentEntity.getType(),
                paymentEntity.getMethod(),
                paymentEntity.getPaymentDate()
        );
    }

    @Override
    public Payment getPaymentByBookingId(Long bookingId) {
        PaymentEntity paymentEntity=paymentRepository.getPaymentByBookingId(bookingId)
                .orElseThrow(()->new RuntimeException("No matching Booking Id"));

        return new Payment(
                paymentEntity.getId(),
                paymentEntity.getBookingId(),
                paymentEntity.getAmount(),
                paymentEntity.getType(),
                paymentEntity.getMethod(),
                paymentEntity.getPaymentDate()
        );
    }
}
