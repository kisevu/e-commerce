package com.ameda.employee.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EmployeeResponse {
    private String names;
    private String email;
    private String phoneNumber;
    private AddressResponse addressResponse;
}
