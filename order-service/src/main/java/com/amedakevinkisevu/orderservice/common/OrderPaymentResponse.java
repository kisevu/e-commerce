package com.amedakevinkisevu.orderservice.common;

import com.amedakevinkisevu.orderservice.entity.Order;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPaymentResponse {
    private Order order;
    private Payment payment;
}
