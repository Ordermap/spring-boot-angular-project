package com.delta.poc.bo;

import java.util.List;


import com.delta.poc.excp.EmployeeExistException;
import com.delta.poc.excp.EmployeeNotFoundException;
import com.delta.poc.vo.Empl;
public interface EmplBoIfc {
	public List<Empl> findAllEmployees()throws EmployeeNotFoundException;
	public Empl findEmployeeById(Integer id)throws EmployeeNotFoundException;
	public String insertEmployee(Empl empl)throws EmployeeExistException, EmployeeNotFoundException;
	public String updateEmployee(Integer empId, Empl empl)throws EmployeeNotFoundException;
	public String deleteEmployee(Integer empId)throws EmployeeNotFoundException;
	 List<Empl>sortEmployees() throws EmployeeNotFoundException;

}
