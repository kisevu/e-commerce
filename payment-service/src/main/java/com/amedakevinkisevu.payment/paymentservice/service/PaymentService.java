package com.amedakevinkisevu.payment.paymentservice.service;

import com.amedakevinkisevu.payment.paymentservice.entity.Payment;
import com.amedakevinkisevu.payment.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        String id = setId();
        payment.setPaymentId(id);
        System.out.println(id);
        return paymentRepository.save(payment);
    }
    public String setId(){
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12);
    }
    public String paymentProcessing(){
        //this api should be 3rd party payment gateway such as paypal or wise or payoneer.
        return new Random().nextBoolean()?"success":"false";
    }
}
