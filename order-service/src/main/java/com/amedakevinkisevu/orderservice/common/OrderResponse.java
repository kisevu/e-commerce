package com.amedakevinkisevu.orderservice.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String  orderId;
    private String name;
    private int qty;
    private double price;
}
