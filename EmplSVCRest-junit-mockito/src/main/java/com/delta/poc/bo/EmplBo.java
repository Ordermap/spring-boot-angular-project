package com.delta.poc.bo;

import java.util.Collections;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.delta.poc.dao.EmplDaoIfc;
import com.delta.poc.excp.EmployeeExistException;
import com.delta.poc.excp.EmployeeNotFoundException;
import com.delta.poc.util.DesignationComparator;
import com.delta.poc.vo.Empl;
@Service
public class EmplBo implements EmplBoIfc {
	@Autowired
	EmplDaoIfc emplDao;
	public List<Empl> findAllEmployees()throws EmployeeNotFoundException {

		List<Empl>listOfEmployees = emplDao.findAllEmployees();
		if (!listOfEmployees.isEmpty()) {
			return listOfEmployees;

		} else {
			throw new EmployeeNotFoundException("employee not found");
		}

	}
	public Empl findEmployeeById(Integer id) throws EmployeeNotFoundException {
		Empl empl =emplDao.findEmployeeById(id);
		if (empl!= null) {
			return empl;
		} else {
			throw new EmployeeNotFoundException("employee not found with id : " + id);
		}

	}
	public String insertEmployee(Empl empl) throws EmployeeExistException, EmployeeNotFoundException {
		Empl empl1 =emplDao.findEmployeeById(empl.getEmpId());
		if (empl1!=null) {
			throw new EmployeeExistException("employee id already exist");
		}
		emplDao.insertEmployee(empl);
		return "employee inserted successfully";
	}
	public String updateEmployee(Integer empId, Empl empl) throws EmployeeNotFoundException {
		Empl empl1 = emplDao.findEmployeeById(empId);
		if (empl1!= null) {
			emplDao.updateEmployee(empl,empId);
			return "employee updated successfully";
		} else {
			throw new EmployeeNotFoundException("employee not found with id : " + empId);
		}

	}
	public String deleteEmployee(Integer empId) throws EmployeeNotFoundException {
		Empl empl = emplDao.findEmployeeById(empId);
		if (empl!= null) {
			emplDao.deleteEmployee(empId);
			return "employee deleted successfully";
		} else {
			throw new EmployeeNotFoundException("employee not found with id : " + empId);
		}
	}
	public List<Empl>sortEmployees() throws EmployeeNotFoundException {
		List<Empl>listOfEmployees=emplDao.findAllEmployees();
		if (!listOfEmployees.isEmpty()) {
			Collections.sort(listOfEmployees,new DesignationComparator());
			return listOfEmployees;

		} else {
			throw new EmployeeNotFoundException("Employee not exist");
		}
		
		
	}


}
