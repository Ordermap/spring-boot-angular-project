package com.example.springboottestapi.payload;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EmployeeRequest {

    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;
}
