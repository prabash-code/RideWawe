package edu.icet.controller;

import edu.icet.model.dto.request.PaymentRequest;
import edu.icet.model.dto.response.PaymentResponse;
import edu.icet.service.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {
    @Autowired
    PaymentServices paymentServices;

    //create new payments
    @PostMapping
    public PaymentResponse createPayment(@RequestBody PaymentRequest payment, Authentication authentication){
        String email = authentication.getName();
       return paymentServices.createNewPayment(payment,email);
    }

    //get all payments
    @GetMapping
    public List<PaymentResponse> getAllPayments(){
       return paymentServices.getAllPayments();
    }

    //get payment by id
    @GetMapping("id/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id){
       return paymentServices.getPaymentById(id);
    }

    //get payment by booking id
    @GetMapping("booking/{bookingId}")
    public List<PaymentResponse> getPaymentByBookingId(@PathVariable Long bookingId){
        return paymentServices.getPaymentByBookingId(bookingId);
    }
}
