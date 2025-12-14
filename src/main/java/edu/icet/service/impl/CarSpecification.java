package edu.icet.service.impl;

import edu.icet.model.entity.CarEntity;
import edu.icet.model.entity.CarType;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {
    public static Specification<CarEntity> hasBrand(String brand) {
        Specification<CarEntity> brand1 = (root, query, cb) ->
                brand == null ? null : cb.equal(cb.lower(root.get("brand")), brand.toLowerCase());
        return brand1;
    }

    public static Specification<CarEntity> hasType(CarType type) {
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("carType"), type);
    }

    public static Specification<CarEntity> minPrice(double minPrice) {
        return (root, query, cb) ->
                minPrice == 0.00 ? null : cb.greaterThanOrEqualTo(root.get("dailyRentalPrice"), minPrice);
    }

    public static Specification<CarEntity> maxPrice(double maxPrice) {
        return (root, query, cb) ->
                maxPrice == 0.00 ? null : cb.lessThanOrEqualTo(root.get("dailyRentalPrice"), maxPrice);
    }

}
