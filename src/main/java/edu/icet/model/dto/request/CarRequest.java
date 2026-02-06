package edu.icet.model.dto.request;

import edu.icet.model.entity.CarType;
import edu.icet.model.entity.FuelType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Model is required")
    private String model;


    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotNull(message = "Year is required")
    @Min(value = 2000, message = "Year must be >= 2000")
    @Max(value = 2025, message = "Year must be <= 2025")
    private Integer year;

    @NotNull(message = "Fuel type is required")
    private String fuelType;

    @NotNull(message = "Seating capacity is required")
    @Min(value = 2, message = "Minimum seating is 2")
    @Max(value = 15, message = "Maximum seating is 15")
    private Integer seatingCapacity;


    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private double dailyRentalPrice;

    @Size(max = 1000, message = "Description max length is 1000")
    private String description;


    private MultipartFile image;
}
