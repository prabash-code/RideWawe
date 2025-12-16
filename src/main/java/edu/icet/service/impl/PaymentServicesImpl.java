package edu.icet.service.impl;

import edu.icet.model.dto.request.PaymentRequest;
import edu.icet.model.dto.response.PaymentResponse;
import edu.icet.model.entity.PaymentEntity;
import edu.icet.repository.PaymentRepository;
import edu.icet.service.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServicesImpl implements PaymentServices  {
    @Autowired
    PaymentRepository paymentRepository;

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

    //create payment
    @Override
    public PaymentResponse createNewPayment(PaymentRequest payment) {
      PaymentEntity paymentEntity=new PaymentEntity();

      paymentEntity.setMethod(payment.getMethod());
      paymentEntity.setBookingId(payment.getBookingId());

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
