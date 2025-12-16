package edu.icet.service;

import edu.icet.model.dto.request.PaymentRequest;
import edu.icet.model.dto.response.PaymentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentServices {
        List<PaymentResponse> getAllPayments();

    PaymentResponse createNewPayment(PaymentRequest payment);

    PaymentResponse getPaymentById(Long id);

    PaymentResponse getPaymentByBookingId(Long bookingId);
}
