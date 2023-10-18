package com.ameda.address.service;

import com.ameda.address.dto.AddressModel;
import com.ameda.address.dto.AddressResponse;
import com.ameda.address.entity.Address;
import com.ameda.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;

    public String setId(){
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12);
    }
    public AddressResponse createAddress(AddressModel addressModel) {
        Address address = Address.builder()
                .addressId(setId())
                .county(addressModel.getCounty())
                .town(addressModel.getTown())
                .zipCode(addressModel.getZipCode())
                .build();
        addressRepository.save(address);
        return AddressResponse.builder()
                .county(address.getCounty())
                .town(address.getTown())
                .zipCode(address.getZipCode())
                .build();
    }

    public AddressResponse getAddress(String addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow();
        if(address!=null){
            return AddressResponse.builder()
                    .county(address.getCounty())
                    .town(address.getTown())
                    .zipCode(address.getZipCode())
                    .build();
        }
        return null;
    }
}
