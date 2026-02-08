package edu.icet.model.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotNull
    private Long carId;

    @NotNull
    private Long customerId;

    @NotNull
    private String customerName;

    @NotNull
    private String customerEmail;

    @NotNull
    private String registrationNumber;

    @NotNull
    private String pickupLocation;

    @NotNull
    private String finalLocation;

    @NotNull
    private double  totalAmount;


    @NotNull @Future
    private LocalDateTime startDate;

    @NotNull @Future
    private LocalDateTime endDate;
}
