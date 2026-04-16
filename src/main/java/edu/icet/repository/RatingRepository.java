package edu.icet.repository;

import edu.icet.model.entity.RatingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<RatingsEntity,Long> {
    Optional<RatingsEntity> findCarByCarIdAndUserId(Long carId, Long userId);
}
