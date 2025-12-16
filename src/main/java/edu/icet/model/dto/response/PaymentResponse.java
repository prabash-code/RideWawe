package edu.icet.model.dto.response;

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
public class PaymentResponse {
    private Long id;
    private Long bookingId;
    private Double amount;
    private PaymentsType type;
    private PaymentMethod method;
    private LocalDateTime paymentDate;
}
