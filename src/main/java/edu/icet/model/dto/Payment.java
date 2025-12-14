package edu.icet.model.dto;


import edu.icet.model.entity.PaymentMethod;
import edu.icet.model.entity.PaymentsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private Long id;
    private Long bookingId;
    private Double amount;
    private PaymentsType type;
    private PaymentMethod method;
    private LocalDateTime paymentDate;
}
