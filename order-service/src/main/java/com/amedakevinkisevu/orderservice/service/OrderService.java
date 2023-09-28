package com.amedakevinkisevu.orderservice.service;

import com.amedakevinkisevu.orderservice.common.Payment;
import com.amedakevinkisevu.orderservice.common.TransactionRequest;
import com.amedakevinkisevu.orderservice.common.TransactionResponse;
import com.amedakevinkisevu.orderservice.dto.OrderDTO;
import com.amedakevinkisevu.orderservice.entity.Order;
import com.amedakevinkisevu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.OrderBy;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public TransactionResponse bookOrder(TransactionRequest request) {
        String message = "";
        OrderDTO orderDTO = request.getOrderDTO();
        Order order = Order.builder()
                .orderId(setId())
                .name(orderDTO.getName())
                .qty(orderDTO.getQty())
                .price(orderDTO.getPrice())
                .build();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getOrderId());
        payment.setAmount(order.getPrice());
        Payment paymentResponse = restTemplate.
                postForObject("http://PAYMENT-SERVICE/payment/doPayment",
                        payment, Payment.class);
        assert paymentResponse != null;
        message= paymentResponse.getPaymentStatus().equals("success")?"successfully initiated payment":"error from 3rd API";
        orderRepository.save(order);
        return TransactionResponse.builder()
                .order(order)
                .amount(paymentResponse.getAmount())
                .transactionId(paymentResponse.getTransactionId())
                .message(message)
                .build();
    }
    public String setId(){
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12);
    }
}
