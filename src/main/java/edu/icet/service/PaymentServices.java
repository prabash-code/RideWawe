package edu.icet.service;

import edu.icet.model.dto.Payment;
import edu.icet.model.entity.PaymentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentServices {
        List<Payment> getAllPayments();

    Payment createNewPayment(Payment payment);

    Payment getPaymentById(Long id);

    Payment getPaymentByBookingId(Long bookingId);
}
