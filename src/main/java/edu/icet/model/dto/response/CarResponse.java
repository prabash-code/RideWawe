package edu.icet.model.dto.response;

import edu.icet.model.entity.CarStatus;
import edu.icet.model.entity.CarType;
import edu.icet.model.entity.FuelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CarResponse {
    private Long id;
    private String brand;
    private String model;
    private CarType type;
    private FuelType fuelType;
    private String registrationNumber;
    private Integer year;
    private Integer seatingCapacity;
    private double dailyRentalPrice;
    private CarStatus status;
    private String description;
    private String imageBase64;
    private  String imageType;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;



            public CarResponse(Long id,String model, String brand, CarType type, FuelType fuelType,
                String registrationNumber, Integer year, Integer seatingCapacity,
        double dailyRentalPrice, CarStatus status, String description,
        byte[] image, String imageType, LocalDateTime createDate,
                LocalDateTime updateDate) {
            this.id = id;
            this.model=model;
            this.brand = brand;
            this.type = type;
            this.fuelType = fuelType;
            this.registrationNumber = registrationNumber;
            this.year = year;
            this.seatingCapacity = seatingCapacity;
            this.dailyRentalPrice = dailyRentalPrice;
            this.status = status;
            this.description = description;
            this.imageType = imageType;
            this.createDate = createDate;
            this.updateDate = updateDate;

            // Convert byte[] to Base64 in service layer result
            if (image != null && image.length > 0) {
                this.imageBase64 = Base64.getEncoder().encodeToString(image);
            }
    }

}
