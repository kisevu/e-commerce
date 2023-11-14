package com.amedakevinkisevu.payment.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payments_table")
public class Payment {
    @Id
    private String paymentId;
    private String paymentStatus;
    private  String transactionId;
    private String orderId;
    private double amount;
}
