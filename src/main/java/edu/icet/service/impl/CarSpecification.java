package edu.icet.service.impl;

import edu.icet.model.entity.CarEntity;
import edu.icet.model.entity.CarType;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {

    public static Specification<CarEntity> hasBrand(String brand) {
        return (root, query, cb) -> {
            if (brand == null || brand.isBlank()) {
                return cb.conjunction();
            }
            return cb.equal(cb.lower(root.get("brand")), brand.toLowerCase());
        };
    }

    public static Specification<CarEntity> hasType(CarType type) {
        return (root, query, cb) -> {
            if (type == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("type"), type);
        };
    }

    public static Specification<CarEntity> minPrice(Double minPrice) {
        return (root, query, cb) -> {
            if (minPrice == null || minPrice <= 0.0) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get("dailyRentalPrice"), minPrice);
        };
    }

    public static Specification<CarEntity> maxPrice(Double maxPrice) {
        return (root, query, cb) -> {
            if (maxPrice == null || maxPrice <= 0.0) {
                return cb.conjunction();
            }
            return cb.lessThanOrEqualTo(root.get("dailyRentalPrice"), maxPrice);
        };
    }
}
