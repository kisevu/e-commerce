package com.amedakevinkisevu.orderservice.common;

import com.amedakevinkisevu.orderservice.entity.Order;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private Order order;
    private double amount;
    private String transactionId;
    private String message;
}
