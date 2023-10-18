package com.ameda.address.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private String county;
    private String town;
    private String zipCode;
}
