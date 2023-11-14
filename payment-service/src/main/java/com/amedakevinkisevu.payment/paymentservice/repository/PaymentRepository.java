package com.amedakevinkisevu.payment.paymentservice.repository;


import com.amedakevinkisevu.payment.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
    Optional<Payment> findPaymentByOrderId(String orderId);
}
