package com.ameda.employee.controller;

import com.ameda.employee.dto.EmployeeModel;
import com.ameda.employee.dto.EmployeeResponse;
import com.ameda.employee.entity.Employee;
import com.ameda.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeModel employeeModel){
        EmployeeResponse employeeResponse = employeeService.registerEmployee(employeeModel);
        return new ResponseEntity<>(employeeResponse,HttpStatus.CREATED);
    }
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable("employeeId") String employeeId){
        EmployeeResponse employeeResponse  = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(employeeResponse,HttpStatus.FOUND);
    }
}
