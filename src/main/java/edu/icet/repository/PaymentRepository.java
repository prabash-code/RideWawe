package edu.icet.repository;

import edu.icet.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
    List<PaymentEntity> findByBookingId(Long bookingId);
}
