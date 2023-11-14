package com.amedakevinkisevu.orderservice.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String paymentId;
    private String paymentStatus;
    private String transactionId;
    private String orderId;
    private double amount;
}
