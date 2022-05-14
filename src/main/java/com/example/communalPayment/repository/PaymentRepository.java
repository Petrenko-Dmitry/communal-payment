package com.example.communalPayment.repository;

import com.example.communalPayment.entity.Payment;
import com.example.communalPayment.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByPaymentStatus(PaymentStatus status);
}
