package edu.icet.service.impl;

import edu.icet.model.dto.request.CarRequest;
import edu.icet.model.dto.response.CarResponse;
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

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;

    @Override
    public CarResponse addNewCar(CarRequest car) {

        CarEntity carEntity = new CarEntity();

        carEntity.setBrand(car.getBrand());
        carEntity.setType(CarType.valueOf(car.getType()));
        carEntity.setFuelType(car.getFuelType());
        carEntity.setRegistrationNumber(car.getRegistrationNumber());
        carEntity.setYear(car.getYear());
        carEntity.setSeatingCapacity(car.getSeatingCapacity());
        carEntity.setDailyRentalPrice(car.getDailyRentalPrice());
        carEntity.setDescription(car.getDescription());
        carEntity.setImageUrl(car.getImageUrl());

        return new CarResponse(carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getType(),
                carEntity.getFuelType(),
                carEntity.getRegistrationNumber(),
                carEntity.getYear(),
                carEntity.getSeatingCapacity(),
                carEntity.getDailyRentalPrice(),
                carEntity.getStatus(),
                carEntity.getDescription(),
                carEntity.getImageUrl(),
                carEntity.getCreateDate(),
                carEntity.getUpdateDate()
        );
    }

    @Override
    public List<CarResponse> getAllCars() {

        List<CarResponse> carList = new ArrayList<>();
        List<CarEntity> allCars = carRepository.findAll();

        for (CarEntity carEntity : allCars) {
            carList.add(new CarResponse(
                    carEntity.getId(),
                    carEntity.getBrand(),
                    carEntity.getType(),
                    carEntity.getFuelType(),
                    carEntity.getRegistrationNumber(),
                    carEntity.getYear(),
                    carEntity.getSeatingCapacity(),
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
    public CarResponse searchCarById(Long id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Car not found with id "+id));
        return new CarResponse(carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getType(),
                carEntity.getFuelType(),
                carEntity.getRegistrationNumber(),
                carEntity.getYear(),
                carEntity.getSeatingCapacity(),
                carEntity.getDailyRentalPrice(),
                carEntity.getStatus(),
                carEntity.getDescription(),
                carEntity.getImageUrl(),
                carEntity.getCreateDate(),
                carEntity.getUpdateDate());
    }

    @Override
    public CarResponse updateCarDetails(Long id,CarRequest car) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Car not found with id "+id));

        carEntity.setBrand(car.getBrand());
        carEntity.setType(CarType.valueOf(car.getType()));
        carEntity.setFuelType(car.getFuelType());
        carEntity.setRegistrationNumber(car.getRegistrationNumber());
        carEntity.setYear(car.getYear());
        carEntity.setSeatingCapacity(car.getSeatingCapacity());
        carEntity.setDailyRentalPrice(car.getDailyRentalPrice());
        carEntity.setDescription(car.getDescription());
        carEntity.setImageUrl(car.getImageUrl());

        return new CarResponse(
                carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getType(),
                carEntity.getFuelType(),
                carEntity.getRegistrationNumber(),
                carEntity.getYear(),
                carEntity.getSeatingCapacity(),
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
    public List<CarResponse> getAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<CarEntity> all = carRepository.findAll();
        List<CarResponse> available=new ArrayList<>();
        for(CarEntity carEntity:all){
            if (carEntity.getStatus().toString()=="AVAILABLE") {
                available.add(new CarResponse(
                        carEntity.getId(),
                        carEntity.getBrand(),
                        carEntity.getType(),
                        carEntity.getFuelType(),
                        carEntity.getRegistrationNumber(),
                        carEntity.getYear(),
                        carEntity.getSeatingCapacity(),
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
    public List<CarResponse> searchCars(String brand, CarType car, double minPrice, double maxPrice) {
        Specification<CarEntity>spec=
                 CarSpecification.hasBrand(brand)
                .and(CarSpecification.hasType(car))
                .and(CarSpecification.minPrice(minPrice))
                .and(CarSpecification.maxPrice(maxPrice));


    return carRepository.findAll(spec).stream()
            .map(carEntity -> {
            return new CarResponse( carEntity.getId(),
                    carEntity.getBrand(),
                    carEntity.getType(),
                    carEntity.getFuelType(),
                    carEntity.getRegistrationNumber(),
                    carEntity.getYear(),
                    carEntity.getSeatingCapacity(),
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
