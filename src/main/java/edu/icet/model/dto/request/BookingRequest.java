package edu.icet.model.dto.request;

import edu.icet.model.entity.CarStatus;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {
    @NotNull
    private Long carId;

    @NotNull @Future
    private LocalDate startDate;

    @NotNull @Future
    private LocalDate endDate;
}
