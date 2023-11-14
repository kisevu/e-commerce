package com.amedakevinkisevu.orderservice.controller;

import com.amedakevinkisevu.orderservice.common.OrderPaymentResponse;
import com.amedakevinkisevu.orderservice.common.TransactionRequest;
import com.amedakevinkisevu.orderservice.common.TransactionResponse;
import com.amedakevinkisevu.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    public static final String ORDER_SERVICE="orderService";
    @PostMapping("/bookOrder")
    @CircuitBreaker(name = ORDER_SERVICE,fallbackMethod = "bookOrderFallbackMethod")
    public ResponseEntity<?> bookOrder(@RequestBody TransactionRequest request){
        TransactionResponse response = orderService.bookOrder(request);
        if(response==null){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }
    public ResponseEntity<TransactionResponse> bookOrderFallbackMethod(Exception e){
       log.info("Payment service is down, wait for few seconds to get it back up...");
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderPaymentData(@PathVariable("orderId") String orderId){
        OrderPaymentResponse response = orderService.getOrder(orderId);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
