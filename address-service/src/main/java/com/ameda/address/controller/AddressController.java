package com.ameda.address.controller;

import com.ameda.address.dto.AddressModel;
import com.ameda.address.dto.AddressResponse;
import com.ameda.address.entity.Address;
import com.ameda.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
@Slf4j
public class AddressController {
    private final AddressService addressService;
    @PostMapping("/create")
    public ResponseEntity<?> createAddress(@RequestBody AddressModel addressModel){
        AddressResponse  addressResponse = addressService.createAddress(addressModel);
        return ResponseEntity.ok(addressResponse);
    }
    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddress(@PathVariable("addressId") String addressId){
        AddressResponse addressResponse = addressService.getAddress(addressId);
        return ResponseEntity.ok(addressResponse);
    }
}
