package com.ameda.address.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "addresses")
public class Address {
    @Id
    private String addressId;
    private String county;
    private String town;
    private String zipCode;
}
