package edu.icet.controller;


import edu.icet.model.entity.CarEntity;
import edu.icet.model.entity.RatingsEntity;
import edu.icet.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RatingsController {

    private final RatingService ratingService;

    @PostMapping("/{carId}")
    public RatingsEntity rateCar(@PathVariable Long carId,
                                 @RequestParam int rating,
                                 @RequestParam Long userId) {
        return ratingService.addRating(carId,rating,userId);
    }

    @GetMapping
    public List<RatingsEntity> getAllRatings() {
        return ratingService.getAllRatings();
    }

}
