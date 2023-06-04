package com.delta.poc.vo;

import javax.validation.constraints.Max;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
@Builder
public class Empl {
	private int empId;
    @NotBlank(message = "employeename can't be blank")
    private String empName;
    @Min(value=18,message="employeeage should be atleast 18")
    @Max(value=60,message="employeeage should not be greater than 60")
    private Integer empAge ;
    @NotBlank(message="employeeDesignation can't be blank")
    private String empDesignation;
    @NotBlank(message="employeeLocation can't be blank")
    private String empLocation;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	public String getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	public String getEmpLocation() {
		return empLocation;
	}
	public void setEmpLocation(String empLocation) {
		this.empLocation = empLocation;
	}
	
	public Empl() {
		super();
	}
	public Empl(int empId, @NotBlank(message = "employeename can't be blank") String empName,
			@Min(value = 18, message = "employeeage should be atleast 18") @Max(value = 60, message = "employeeage should not be greater than 60") Integer empAge,
			@NotBlank(message = "employeeDesignation can't be blank") String empDesignation,
			@NotBlank(message = "employeeLocation can't be blank") String empLocation) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.empDesignation = empDesignation;
		this.empLocation = empLocation;
	}
	
	
	
    

}
