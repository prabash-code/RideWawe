package edu.icet.service.impl;

import edu.icet.model.dto.request.CarRequest;
import edu.icet.model.dto.response.CarResponse;
import edu.icet.model.entity.CarEntity;
import edu.icet.model.entity.CarStatus;
import edu.icet.model.entity.CarType;
import edu.icet.model.entity.FuelType;
import edu.icet.repository.CarRepository;
import edu.icet.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public CarResponse addNewCar(CarRequest car) {
        try {
            if (car.getType() == null || car.getType().isBlank()) {
                throw new RuntimeException("Car type is required");
            }

            CarEntity carEntity = new CarEntity();

            carEntity.setBrand(car.getBrand());
            try {
                carEntity.setType(CarType.valueOf(car.getType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid Car Type " + car.getType());
            }

            try {
                carEntity.setFuelType(FuelType.valueOf(car.getFuelType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid Fuel Type " + car.getFuelType());
            }

            carEntity.setRegistrationNumber(car.getRegistrationNumber());
            carEntity.setYear(Integer.valueOf(car.getYear()));
            carEntity.setSeatingCapacity(Integer.valueOf(car.getSeatingCapacity()));
            carEntity.setDailyRentalPrice(car.getDailyRentalPrice());
            carEntity.setDescription(car.getDescription());


            try {
                carEntity.setStatus(CarStatus.valueOf(car.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid Status " + car.getStatus());
            }

            handleImageUpload(car.getImage(), carEntity);
            carRepository.save(carEntity);

            return new CarResponse(carEntity.getId(),
                    carEntity.getBrand(),
                    carEntity.getModel(),
                    carEntity.getType(),
                    carEntity.getFuelType(),
                    carEntity.getRegistrationNumber(),
                    carEntity.getYear(),
                    carEntity.getSeatingCapacity(),
                    carEntity.getDailyRentalPrice(),
                    carEntity.getStatus(),
                    carEntity.getDescription(),
                    carEntity.getImage(),
                    carEntity.getImageType(),
                    carEntity.getCreateDate(),
                    carEntity.getUpdateDate(),
                    carEntity.getRatingAverage(),
                    carEntity.getRatingCount()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CarResponse> getAllCars() {

        List<CarResponse> carList = new ArrayList<>();
        List<CarEntity> allCars = carRepository.findAll();

        for (CarEntity carEntity : allCars) {
            carList.add(new CarResponse(
                    carEntity.getId(),
                    carEntity.getBrand(),
                    carEntity.getModel(),
                    carEntity.getType(),
                    carEntity.getFuelType(),
                    carEntity.getRegistrationNumber(),
                    carEntity.getYear(),
                    carEntity.getSeatingCapacity(),
                    carEntity.getDailyRentalPrice(),
                    carEntity.getStatus(),
                    carEntity.getDescription(),
                    carEntity.getImage(),
                    carEntity.getImageType(),
                    carEntity.getCreateDate(),
                    carEntity.getUpdateDate(),
                    carEntity.getRatingAverage(),
                    carEntity.getRatingCount()
            ));
        }
        return carList;
    }

    @Override
    public CarResponse searchCarById(Long id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
        return new CarResponse(carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getModel(),
                carEntity.getType(),
                carEntity.getFuelType(),
                carEntity.getRegistrationNumber(),
                carEntity.getYear(),
                carEntity.getSeatingCapacity(),
                carEntity.getDailyRentalPrice(),
                carEntity.getStatus(),
                carEntity.getDescription(),
                carEntity.getImage(),
                carEntity.getImageType(),
                carEntity.getCreateDate(),
                carEntity.getUpdateDate(),
                carEntity.getRatingAverage(),
                carEntity.getRatingCount());
    }

    @Override
    public CarResponse updateCarDetails(Long id, CarRequest car) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));

        carEntity.setBrand(car.getBrand());
        try {
            carEntity.setType(CarType.valueOf(car.getType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Car Type " + car.getType());
        }

        try {
            carEntity.setFuelType(FuelType.valueOf(car.getFuelType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Fuel Type " + car.getFuelType());
        }

        carEntity.setRegistrationNumber(car.getRegistrationNumber());
        carEntity.setYear(Integer.valueOf(car.getYear()));
        carEntity.setSeatingCapacity(Integer.valueOf(car.getSeatingCapacity()));
        carEntity.setDailyRentalPrice(car.getDailyRentalPrice());
        carEntity.setDescription(car.getDescription());

        try {
            carEntity.setStatus(CarStatus.valueOf(car.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Status " + car.getStatus());
        }

        handleImageUpload(car.getImage(), carEntity);
        carRepository.save(carEntity);

        return new CarResponse(
                carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getModel(),
                carEntity.getType(),
                carEntity.getFuelType(),
                carEntity.getRegistrationNumber(),
                carEntity.getYear(),
                carEntity.getSeatingCapacity(),
                carEntity.getDailyRentalPrice(),
                carEntity.getStatus(),
                carEntity.getDescription(),
                carEntity.getImage(),
                carEntity.getImageType(),
                carEntity.getCreateDate(),
                carEntity.getUpdateDate(),
                carEntity.getRatingAverage(),
                carEntity.getRatingCount()

        );
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);

    }

    @Override
    public List<CarResponse> getAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<CarEntity> all = carRepository.findAll();
        List<CarResponse> available = new ArrayList<>();
        for (CarEntity carEntity : all) {
            if ("AVAILABLE".equals(carEntity.getStatus().toString())) {
                available.add(new CarResponse(
                        carEntity.getId(),
                        carEntity.getBrand(),
                        carEntity.getModel(),
                        carEntity.getType(),
                        carEntity.getFuelType(),
                        carEntity.getRegistrationNumber(),
                        carEntity.getYear(),
                        carEntity.getSeatingCapacity(),
                        carEntity.getDailyRentalPrice(),
                        carEntity.getStatus(),
                        carEntity.getDescription(),
                        carEntity.getImage(),
                        carEntity.getImageType(),
                        carEntity.getCreateDate(),
                        carEntity.getUpdateDate(),
                        carEntity.getRatingAverage(),
                        carEntity.getRatingCount()
                ));

            }

        }

        return available;
    }

    @Override
    public List<CarResponse> searchCars(String brand, CarType car, Double minPrice, Double maxPrice) {
        Specification<CarEntity> spec = null;


        if (brand != null && !brand.isBlank()) {
            spec = CarSpecification.hasBrand(brand);
        }

        if (car != null) {
            spec = (spec == null) ? CarSpecification.hasType(car) : spec.and(CarSpecification.hasType(car));
        }

        if (minPrice != null && minPrice > 0) {
            spec = (spec == null) ? CarSpecification.minPrice(minPrice) : spec.and(CarSpecification.minPrice(minPrice));
        }

        if (maxPrice != null && maxPrice > 0) {
            spec = (spec == null) ? CarSpecification.maxPrice(maxPrice) : spec.and(CarSpecification.maxPrice(maxPrice));
        }

        List<CarEntity> result = (spec == null) ? carRepository.findAll() : carRepository.findAll(spec);

        return result.stream()
                .map(carEntity -> new CarResponse(
                        carEntity.getId(),
                        carEntity.getBrand(),
                        carEntity.getModel(),
                        carEntity.getType(),
                        carEntity.getFuelType(),
                        carEntity.getRegistrationNumber(),
                        carEntity.getYear(),
                        carEntity.getSeatingCapacity(),
                        carEntity.getDailyRentalPrice(),
                        carEntity.getStatus(),
                        carEntity.getDescription(),
                        carEntity.getImage(),
                        carEntity.getImageType(),
                        carEntity.getCreateDate(),
                        carEntity.getUpdateDate(),
                        carEntity.getRatingAverage(),
                        carEntity.getRatingCount()
                ))
                .toList();
    }

    @Override
    public CarResponse updateRatings(Long id, int newRating) {

        if (newRating < 1 || newRating > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        CarEntity car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        int count = car.getRatingCount();
        double avg = car.getRatingAverage();

        double total = avg * count;

        count++;
        double newAverage = (total + newRating) / count;

        newAverage = Math.round(newAverage * 10.0) / 10.0;

        car.setRatingCount(count);
        car.setRatingAverage(newAverage);

        carRepository.save(car);

        return new CarResponse(
                car.getId(),
                car.getModel(),
                car.getBrand(),
                car.getType(),
                car.getFuelType(),
                car.getRegistrationNumber(),
                car.getYear(),
                car.getSeatingCapacity(),
                car.getDailyRentalPrice(),
                car.getStatus(),
                car.getDescription(),
                car.getImage(),
                car.getImageType(),
                car.getCreateDate(),
                car.getUpdateDate(),
                car.getRatingAverage(),
                car.getRatingCount()
        );
    }


    private void handleImageUpload(MultipartFile imageFile, CarEntity carEntity) {
        if (imageFile != null && !imageFile.isEmpty()) {

            validateImageFile(imageFile);
            try {
                carEntity.setImage(imageFile.getBytes());
                carEntity.setImageType(imageFile.getContentType());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void validateImageFile(MultipartFile file) {
        long maxSize = 10 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new RuntimeException("Image file size must not exceed 10MB");
        }

        // Check file type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("Only image files are allowed");
        }

        // Optionally check for specific image types
        List<String> allowedTypes = List.of("image/jpeg", "image/png", "image/jpg", "image/webp");
        if (!allowedTypes.contains(contentType.toLowerCase())) {
            throw new RuntimeException("Only JPEG, PNG, and WEBP images are allowed");
        }
    }

}
