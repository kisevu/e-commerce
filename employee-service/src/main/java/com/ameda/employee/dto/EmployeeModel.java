package com.ameda.employee.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeModel {
    private String names;
    private String email;
    private String phoneNumber;
}
