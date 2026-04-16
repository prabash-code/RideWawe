package edu.icet.service.impl;

import edu.icet.model.entity.CarEntity;
import edu.icet.model.entity.RatingsEntity;
import edu.icet.repository.CarRepository;
import edu.icet.repository.RatingRepository;
import edu.icet.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final CarRepository carRepository;
    @Override
    public RatingsEntity addRating(Long carId,int rating,Long userId) {
        CarEntity car=carRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car Not Found"));

        Optional<RatingsEntity>exist =ratingRepository.findCarByCarIdAndUserId(carId,userId);
        if(exist.isPresent()){
            throw new RuntimeException("User Already Rated This Car");
        }

        RatingsEntity rate=new RatingsEntity();
        rate.setUserId(userId);
        rate.setRating(rating);
        rate.setCar(car);
         ratingRepository.save(rate);

        double total=car.getRatingAverage();
        car.setRatingCount(car.getRatingCount()+1);
        car.setRatingAverage((total+rating)/car.getRatingCount());
        carRepository.save(car);
        return rate;


    }

    @Override
    public List<RatingsEntity> getAllRatings() {
        List<RatingsEntity> ratings = ratingRepository.findAll();
        return ratings;
    }
}
