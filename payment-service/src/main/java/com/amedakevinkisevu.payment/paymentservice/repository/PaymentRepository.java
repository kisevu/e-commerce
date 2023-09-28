package com.amedakevinkisevu.payment.paymentservice.repository;


import com.amedakevinkisevu.payment.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
}
