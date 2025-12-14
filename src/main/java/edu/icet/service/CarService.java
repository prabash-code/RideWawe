package edu.icet.service;

import edu.icet.model.dto.Car;
import edu.icet.model.entity.CarType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CarService {
    Car addNewCar(Car car);

    List<Car> getAllCars();

    Car searchCarById(Long id);

    Car updateCarDetails(Long id,Car car);

    void deleteCar(Long id);

    List<Car> getAvailableCars(LocalDate startDate, LocalDate endDate);

    List<Car> searchCars(String brand, CarType car, double minPrice, double maxPrice);
}
