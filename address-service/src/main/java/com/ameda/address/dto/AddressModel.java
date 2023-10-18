package com.ameda.address.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AddressModel {
    private String county;
    private String town;
    private String zipCode;
}
