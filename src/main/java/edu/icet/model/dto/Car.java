package edu.icet.model.dto;

import edu.icet.model.entity.CarStatus;
import edu.icet.model.entity.CarType;
import edu.icet.model.entity.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Long id;
    private String brand;
    private CarType type;
    private FuelType fuelType;
    private String registrationNumber;
    private Integer year;
    private double dailyRentalPrice;
    private CarStatus status;
    private String description;
    private String imageUrl;
    private LocalDateTime  createDate;
    private LocalDateTime updateDate;

}
