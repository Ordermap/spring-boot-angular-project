package com.delta.poc.excp;

import java.util.ArrayList;
import java.util.Date;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.delta.poc.util.ErrDetails;
@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler{
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<FieldError>errors=ex.getBindingResult().getFieldErrors();
		List<String>errorsList=new ArrayList<>();
		for(FieldError error:errors) {
			errorsList.add(error.getField()+" "+error.getDefaultMessage());
		}
		ErrDetails errDetails=new ErrDetails(new Date(),ex.getMessage(),errorsList);
		//return ResponseEntity.badRequest().body(new Date(),ex.getMessage(),new ErrDetails(errorsList));
		return new ResponseEntity<ErrDetails>(errDetails,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrDetails> handleBusinessException_1(EmployeeNotFoundException ex) {
		ErrDetails errDetails=new ErrDetails(new Date(),ex.getMessage());
        return new ResponseEntity<ErrDetails>(errDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EmployeeExistException.class)
    public ResponseEntity<ErrDetails> handleBusinessException_2(EmployeeExistException ex) {
        ErrDetails errDetails=new ErrDetails(new Date(),ex.getMessage());
        return new ResponseEntity<ErrDetails>(errDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
		

	
}
