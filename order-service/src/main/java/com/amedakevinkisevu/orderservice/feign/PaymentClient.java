package com.amedakevinkisevu.orderservice.feign;

import com.amedakevinkisevu.orderservice.common.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PAYMENT-SERVICE",url= "http://localhost:8989",path = "/payment")
public interface PaymentClient { //proxy
    @GetMapping("/payment/{orderId}")
    public ResponseEntity<Payment> paymentByOrderId(@PathVariable("orderId") String orderId);
}
