package com.ameda.employee.entity;

import com.ameda.employee.dto.AddressResponse;
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
@Table(name = "employees")
public class Employee {
    @Id
    private String employeeId;
    private String names;
    private String email;
    private String phoneNumber;
}
