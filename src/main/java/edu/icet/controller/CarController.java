package edu.icet.controller;


import edu.icet.model.dto.Car;
import edu.icet.model.entity.CarType;
import edu.icet.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    List<Car> cars;

    @Autowired
    CarService carService;

    //Add car
    @PostMapping
    public Car addCar(Car car) {
        return carService.addNewCar(car);
    }

    //Get all Cars
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }


    //search car by id
    @GetMapping("cars/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.searchCarById(id);

    }

    //update car by id
    @PutMapping("/{id}")
    public Car updateCarDetails(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCarDetails(id,car);
    }


    //delete car
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    //availability of cars
    @GetMapping("/available")
    public List<Car> getAvailableCars(@RequestParam(required = false)LocalDate startDate,
                                      @RequestParam(required = false)LocalDate endDate
                                      ){
        return carService.getAvailableCars(startDate,endDate);
    }

    //search cars
    @GetMapping("/car")
    public List<Car> searchCar(@RequestParam(required = false)String brand,
                               @RequestParam(required = false) CarType car,
                               @RequestParam(required = false) double minPrice,
                               @RequestParam(required = false) double maxPrice
                               ){
        return carService.searchCars(brand,car,minPrice,maxPrice);
    }




}