package com.amedakevinkisevu.payment.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;



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
