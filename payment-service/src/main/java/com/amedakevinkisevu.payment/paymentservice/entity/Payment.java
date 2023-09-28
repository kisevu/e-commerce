package com.amedakevinkisevu.payment.paymentservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {
    @Id
    private String paymentId;
    private String paymentStatus;
    private  String transactionId;
    private String orderId;
    private double amount;
}
