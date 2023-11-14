package com.amedakevinkisevu.payment.paymentservice.controller;
import com.amedakevinkisevu.payment.paymentservice.entity.Payment;
import com.amedakevinkisevu.payment.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/doPayment")
    public ResponseEntity<Payment> doPayment(@RequestBody Payment payment){
        Payment paymentResponse = paymentService.doPayment(payment);
        if(paymentResponse!=null){
            return new ResponseEntity<>(paymentResponse,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/payment/{orderId}")
    public ResponseEntity<Payment> paymentByOrderId(@PathVariable("orderId") String orderId){
        Payment payment = paymentService.paymentByOrderId(orderId);
        if(payment!=null){
            return new ResponseEntity<>(payment,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
