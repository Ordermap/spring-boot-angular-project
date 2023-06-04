package com.example.springboottestapi.exception;

public class EmployeeNotFound extends RuntimeException{
    public EmployeeNotFound(String s) {
        super(s);
    }
}
