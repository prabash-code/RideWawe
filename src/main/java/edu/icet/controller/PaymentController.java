package edu.icet.controller;

import edu.icet.model.dto.Payment;
import edu.icet.service.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    PaymentServices paymentServices;

    //create new payments
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment){
       return paymentServices.createNewPayment(payment);
    }

    //get all payments
    @GetMapping
    public List<Payment> getAllPayments(){
       return paymentServices.getAllPayments();
    }

    //get payment by id
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id){
       return paymentServices.getPaymentById(id);
    }

    //get payment by booking id
    @GetMapping("/{bookingId}")
    public Payment getPaymentByBookingId(@PathVariable Long bookingId){
        return paymentServices.getPaymentByBookingId(bookingId);
    }
}
