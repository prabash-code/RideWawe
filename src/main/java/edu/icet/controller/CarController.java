package edu.icet.controller;


import edu.icet.model.dto.request.CarRequest;
import edu.icet.model.dto.response.CarResponse;
import edu.icet.model.entity.CarType;
import edu.icet.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    List<CarResponse> cars;

    @Autowired
    CarService carService;

    //Add car
    @PostMapping
    public CarResponse addCar(@RequestBody CarRequest car) {
        return carService.addNewCar(car);
    }

    //Get all Cars
    @GetMapping
    public List<CarResponse> getAllCars() {
        return carService.getAllCars();
    }


    //search car by id
    @GetMapping("/{id}")
    public CarResponse getCarById(@PathVariable Long id) {
        return carService.searchCarById(id);

    }

    //update car by id
    @PutMapping("/{id}")
    public CarResponse updateCarDetails(@PathVariable Long id, @RequestBody CarRequest car) {
        return carService.updateCarDetails(id, car);
    }


    //delete car
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    //availability of cars
    @GetMapping("/available")
    public List<CarResponse> getAvailableCars(@RequestParam(required = false) LocalDate startDate,
                                              @RequestParam(required = false) LocalDate endDate
    ) {
        return carService.getAvailableCars(startDate, endDate);
    }

    //search cars
    @GetMapping("/search")
    public List<CarResponse> searchCar(@RequestParam(required = false) String brand,
                                       @RequestParam(required = false) CarType car,
                                       @RequestParam(required = false) double minPrice,
                                       @RequestParam(required = false) double maxPrice
    ) {
        return carService.searchCars(brand, car, minPrice, maxPrice);
    }

}