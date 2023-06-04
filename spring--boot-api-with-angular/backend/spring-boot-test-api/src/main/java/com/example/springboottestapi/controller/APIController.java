package com.example.springboottestapi.controller;

import com.example.springboottestapi.model.Employee;
import com.example.springboottestapi.payload.EmployeeRequest;
import com.example.springboottestapi.payload.ErrorResponse;
import com.example.springboottestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin(value = "http://localhost:4200",maxAge = 3600)
public class APIController {

    @Autowired
    private EmployeeService  employeeService;
    @GetMapping
    public ResponseEntity<?> getEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        System.out.println("employeeRequest = " + employeeRequest);
        try {
            return new ResponseEntity<>(employeeService.addEmployee(employeeRequest), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse("failed to add employee in system. msg: "+e.getMessage()),HttpStatus.BAD_GATEWAY);
        }
    }
}
