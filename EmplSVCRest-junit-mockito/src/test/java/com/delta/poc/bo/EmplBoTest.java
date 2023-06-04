package com.delta.poc.bo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.delta.poc.dao.EmplDaoIfc;
import com.delta.poc.excp.EmployeeExistException;
import com.delta.poc.excp.EmployeeNotFoundException;
import com.delta.poc.svc.EmplSVC;
import com.delta.poc.svc.EmplSVCTest;
import com.delta.poc.vo.Empl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = { EmplBoTest.class })
public class EmplBoTest {
	@Mock
	EmplDaoIfc emplDao;
	@InjectMocks
	EmplBo emplBo;
	List<Empl> listOfEmployees;
	Empl empl;

	@Test
	public void test_findAllEmployees_success() throws EmployeeNotFoundException {
		List<Empl> listOfEmployees = new ArrayList<>();
		listOfEmployees.add(new Empl(1, "Priyanka", 23, "PAT", "Delhi"));
		listOfEmployees.add(new Empl(2, "Isha Singh", 20, "SD", "PUNE"));
		listOfEmployees.add(new Empl(3, "Swapnil Singh", 21, "SE", "Mumbai"));
		Mockito.when(emplDao.findAllEmployees()).thenReturn(listOfEmployees);
		assertEquals(3, emplBo.findAllEmployees().size());

	}

	@Test
	public void test_findAllEmployees_failure() throws EmployeeNotFoundException {
		List<Empl> listOfEmployees = new ArrayList<>();

		Mockito.when(emplDao.findAllEmployees()).thenReturn(listOfEmployees);
		assertThrows(EmployeeNotFoundException.class, () -> {
			emplBo.findAllEmployees();
		});

	}

	@Test
	public void test_findEmployeeById_success() throws EmployeeNotFoundException {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		int empId = 1;
		Mockito.when(emplDao.findEmployeeById(empId)).thenReturn(EMPLOYEE_1);
		assertEquals(empId, emplBo.findEmployeeById(empId).getEmpId());

	}

	@Test
	public void test_findEmployeeById_failure() throws EmployeeNotFoundException {
		int empId = 1;
		Mockito.when(emplDao.findEmployeeById(empId)).thenReturn(null);
		assertThrows(EmployeeNotFoundException.class, () -> {
			emplBo.findEmployeeById(empId);
		});

	}

	@Test
	public void test_insertEmployee_success() throws EmployeeExistException, EmployeeNotFoundException {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		Mockito.when(emplDao.findEmployeeById(EMPLOYEE_1.getEmpId())).thenReturn(null);
		String msg = emplBo.insertEmployee(EMPLOYEE_1);
		verify(emplDao, times(1)).insertEmployee(EMPLOYEE_1);
		assertEquals("employee inserted successfully", msg);
	}

	@Test
	public void test_insertEmployee_failure() throws EmployeeExistException, EmployeeNotFoundException {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		Mockito.when(emplDao.findEmployeeById(EMPLOYEE_1.getEmpId())).thenReturn(EMPLOYEE_1);
		assertThrows(EmployeeExistException.class, () -> {
			emplBo.insertEmployee(EMPLOYEE_1);
		});
	}

	@Test
	public void test_updateEmployee_success() throws EmployeeNotFoundException {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		Mockito.when(emplDao.findEmployeeById(EMPLOYEE_1.getEmpId())).thenReturn(EMPLOYEE_1);
		String msg = emplBo.updateEmployee(1, EMPLOYEE_1);

		verify(emplDao, times(1)).updateEmployee(EMPLOYEE_1, EMPLOYEE_1.getEmpId());
		assertEquals("employee updated successfully", msg);
	}

	@Test
	public void test_updateEmployee_failure() {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		Mockito.when(emplDao.findEmployeeById(1)).thenReturn(null);
		assertThrows(EmployeeNotFoundException.class, () -> {
			emplBo.updateEmployee(1, EMPLOYEE_1);
		});

	}

	@Test
	public void test_deleteEmployee_success() throws EmployeeNotFoundException {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		Mockito.when(emplDao.findEmployeeById(EMPLOYEE_1.getEmpId())).thenReturn(EMPLOYEE_1);
		String msg = emplBo.deleteEmployee(1);
		verify(emplDao, times(1)).deleteEmployee(EMPLOYEE_1.getEmpId());
		assertEquals("employee deleted successfully", msg);

	}

	@Test
	public void test_deleteEmployee_failure() {
		Mockito.when(emplDao.findEmployeeById(1)).thenReturn(null);
		assertThrows(EmployeeNotFoundException.class, () -> {
			emplBo.deleteEmployee(1);
		});

	}

}
