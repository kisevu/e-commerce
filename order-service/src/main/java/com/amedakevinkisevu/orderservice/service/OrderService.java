package com.amedakevinkisevu.orderservice.service;

import com.amedakevinkisevu.orderservice.common.*;
import com.amedakevinkisevu.orderservice.dto.OrderDTO;
import com.amedakevinkisevu.orderservice.entity.Order;
import com.amedakevinkisevu.orderservice.feign.PaymentClient;
import com.amedakevinkisevu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    @Autowired
    private  PaymentClient paymentClient;

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
    public OrderPaymentResponse getOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        OrderResponse orderResponse = modelMapper.map(order,OrderResponse.class);
        Payment payment = paymentClient.paymentByOrderId(orderId).getBody();
        assert payment != null;
        log.info(payment.getPaymentId(),payment.getPaymentStatus(),
                payment.getTransactionId(),payment.getOrderId(),
                payment.getAmount());
        return OrderPaymentResponse.builder()
                .order(order)
                .payment(payment)
                .build();
    }
}
