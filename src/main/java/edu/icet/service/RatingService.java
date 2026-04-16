package edu.icet.service;

import edu.icet.model.entity.RatingsEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    RatingsEntity addRating(Long carId, int rating, Long userId);

   List<RatingsEntity> getAllRatings();
}
