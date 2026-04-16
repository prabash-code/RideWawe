package edu.icet.model.dto.response;

import edu.icet.model.entity.CarStatus;
import edu.icet.model.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookingResponse {
    private Long id;
    private Long carId;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String registrationNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PaymentStatus paymentStatus;
}
