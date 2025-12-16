package edu.icet.model.dto.request;
import edu.icet.model.entity.PaymentMethod;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotNull
    private Long bookingId;

    @NotNull
    private PaymentMethod method;
}