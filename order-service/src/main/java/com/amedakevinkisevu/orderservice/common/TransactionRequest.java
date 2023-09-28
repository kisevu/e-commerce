package com.amedakevinkisevu.orderservice.common;

import com.amedakevinkisevu.orderservice.dto.OrderDTO;
import com.amedakevinkisevu.orderservice.entity.Order;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private OrderDTO orderDTO;
    private Payment payment;
}
