package edu.icet.service;

import edu.icet.model.dto.request.CarRequest;
import edu.icet.model.dto.response.CarResponse;
import edu.icet.model.entity.CarType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CarService {
    CarResponse addNewCar(CarRequest car);

    List<CarResponse> getAllCars();

    CarResponse searchCarById(Long id);

    CarResponse updateCarDetails(Long id,CarRequest car);

    void deleteCar(Long id);

    List<CarResponse> getAvailableCars(LocalDate startDate, LocalDate endDate);

    List<CarResponse> searchCars(String brand, CarType car, Double minPrice, Double maxPrice);

    CarResponse updateRatings(Long id, int ratings);
}
