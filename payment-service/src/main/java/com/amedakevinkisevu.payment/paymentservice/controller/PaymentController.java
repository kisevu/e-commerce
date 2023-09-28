package com.amedakevinkisevu.payment.paymentservice.controller;
import com.amedakevinkisevu.payment.paymentservice.entity.Payment;
import com.amedakevinkisevu.payment.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<?> doPayment(@RequestBody Payment payment){
        Payment paymentResponse = paymentService.doPayment(payment);
        if(paymentResponse!=null){
            return new ResponseEntity<>(paymentResponse,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
        }
    }
}
