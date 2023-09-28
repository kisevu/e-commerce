package com.amedakevinkisevu.orderservice.controller;

import com.amedakevinkisevu.orderservice.common.TransactionRequest;
import com.amedakevinkisevu.orderservice.common.TransactionResponse;
import com.amedakevinkisevu.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/bookOrder")
    public ResponseEntity<?> bookOrder(@RequestBody TransactionRequest request){
        TransactionResponse response = orderService.bookOrder(request);
        if(response==null){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }
}
