package edu.icet.service.impl;

import edu.icet.model.dto.Car;
import edu.icet.model.entity.CarEntity;
import edu.icet.model.entity.CarType;
import edu.icet.repository.CarRepository;
import edu.icet.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;

    @Override
    public Car addNewCar(Car car) {

        CarEntity carEntity = new CarEntity();
        carEntity.setBrand(car.getBrand());
        carEntity.setType(car.getType());
        carEntity.setFuelType(car.getFuelType());
        carEntity.setRegistrationNumber(car.getRegistrationNumber());
        carEntity.setYear(car.getYear());
        carEntity.setDailyRentalPrice(car.getDailyRentalPrice());
        carEntity.setStatus(car.getStatus());
        carEntity.setDescription(car.getDescription());
        carEntity.setImageUrl(car.getImageUrl());

        return new Car(carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getType(),
                carEntity.getFuelType(),
                carEntity.getRegistrationNumber(),
                carEntity.getYear(),
                carEntity.getDailyRentalPrice(),
                carEntity.getStatus(),
                carEntity.getDescription(),
                carEntity.getImageUrl(),
                carEntity.getCreateDate(),
                carEntity.getUpdateDate()
        );
    }

    @Override
    public List<Car> getAllCars() {

        List<Car> carList = new ArrayList<>();
        List<CarEntity> allCars = carRepository.findAll();

        for (CarEntity carEntity : allCars) {
            carList.add(new Car(
                    carEntity.getId(),
                    carEntity.getBrand(),
                    carEntity.getType(),
                    carEntity.getFuelType(),
                    carEntity.getRegistrationNumber(),
                    carEntity.getYear(),
                    carEntity.getDailyRentalPrice(),
                    carEntity.getStatus(),
                    carEntity.getDescription(),
                    carEntity.getImageUrl(),
                    carEntity.getCreateDate(),
                    carEntity.getUpdateDate()
            ));
        }
        return carList;
    }

    @Override
    public Car searchCarById(Long id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Car not found with id "+id));
        return new Car(carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getType(),
                carEntity.getFuelType(),
                carEntity.getRegistrationNumber(),
                carEntity.getYear(),
                carEntity.getDailyRentalPrice(),
                carEntity.getStatus(),
                carEntity.getDescription(),
                carEntity.getImageUrl(),
                carEntity.getCreateDate(),
                carEntity.getUpdateDate());
    }

    @Override
    public Car updateCarDetails(Long id,Car car) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Car not found with id "+id));

        carEntity.setBrand(car.getBrand());
        carEntity.setType(car.getType());
        carEntity.setFuelType(car.getFuelType());
        carEntity.setRegistrationNumber(car.getRegistrationNumber());
        carEntity.setYear(car.getYear());
        carEntity.setDailyRentalPrice(car.getDailyRentalPrice());
        carEntity.setStatus(car.getStatus());
        carEntity.setDescription(car.getDescription());
        carEntity.setImageUrl(car.getImageUrl());

        return new Car(carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getType(),
                carEntity.getFuelType(),
                carEntity.getRegistrationNumber(),
                carEntity.getYear(),
                carEntity.getDailyRentalPrice(),
                carEntity.getStatus(),
                carEntity.getDescription(),
                carEntity.getImageUrl(),
                carEntity.getCreateDate(),
                carEntity.getUpdateDate()

        );
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);

    }

    @Override
    public List<Car> getAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<CarEntity> all = carRepository.findAll();
        List<Car> available=new ArrayList<>();
        for(CarEntity carEntity:all){
            if (carEntity.getStatus().toString()=="AVAILABLE") {
                available.add(new Car(
                        carEntity.getId(),
                        carEntity.getBrand(),
                        carEntity.getType(),
                        carEntity.getFuelType(),
                        carEntity.getRegistrationNumber(),
                        carEntity.getYear(),
                        carEntity.getDailyRentalPrice(),
                        carEntity.getStatus(),
                        carEntity.getDescription(),
                        carEntity.getImageUrl(),
                        carEntity.getCreateDate(),
                        carEntity.getUpdateDate()
                ));
            }

        }

        return available;
    }

    @Override
    public List<Car> searchCars(String brand, CarType car, double minPrice, double maxPrice) {
        Specification<CarEntity>spec=
                 CarSpecification.hasBrand(brand)
                .and(CarSpecification.hasType(car))
                .and(CarSpecification.minPrice(minPrice))
                .and(CarSpecification.maxPrice(maxPrice));


    return carRepository.findAll(spec).stream()
            .map(carEntity -> {
            return new Car( carEntity.getId(),
                    carEntity.getBrand(),
                    carEntity.getType(),
                    carEntity.getFuelType(),
                    carEntity.getRegistrationNumber(),
                    carEntity.getYear(),
                    carEntity.getDailyRentalPrice(),
                    carEntity.getStatus(),
                    carEntity.getDescription(),
                    carEntity.getImageUrl(),
                    carEntity.getCreateDate(),
                    carEntity.getUpdateDate()

            );
        }
)
            .toList();
    }




}
