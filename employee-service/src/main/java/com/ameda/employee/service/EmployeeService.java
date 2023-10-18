package com.ameda.employee.service;

import com.ameda.employee.dto.AddressResponse;
import com.ameda.employee.dto.EmployeeModel;
import com.ameda.employee.dto.EmployeeResponse;
import com.ameda.employee.entity.Employee;
import com.ameda.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @Value("${address-service.base_url}")
    private String baseURL;

    public String setId(){
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12);
    }
    public EmployeeResponse registerEmployee(EmployeeModel employeeModel) {
        Employee employee = Employee.builder()
                .employeeId(setId())
                .names(employeeModel.getNames())
                .email(employeeModel.getEmail())
                .phoneNumber(employeeModel.getPhoneNumber())
                .build();
        employeeRepository.save(employee);
        return EmployeeResponse.builder()
                .names(employee.getNames())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .build();
    }
    public EmployeeResponse  getEmployee(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        EmployeeResponse employeeResponse = modelMapper.map(employee,EmployeeResponse.class);
        //if you update the application to have a context-path
        //then be rest assured that the mapped id works dynamically for the address...
        AddressResponse addressResponse =restTemplate.getForObject(baseURL+"0abfe242097d", AddressResponse.class,employeeId);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }
}
