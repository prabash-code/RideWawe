package edu.icet.model.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotNull
    private Long id;

    @NotNull @Future
    private LocalDateTime startDate;

    @NotNull @Future
    private LocalDateTime endDate;
}
