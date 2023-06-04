package com.delta.poc.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import com.delta.poc.vo.Empl;
@ComponentScan(basePackages="com.delta.poc")
@ContextConfiguration
@SpringBootTest(classes = { EmplDaoIfcTest.class })


public class EmplDaoIfcTest {

	@Autowired
	EmplDaoIfc emplDao;
	
	@Test
	@Order(1)
	public void test_insert () {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		emplDao.insertEmployee(EMPLOYEE_1);
		assertNotNull(emplDao.findEmployeeById(1).equals(EMPLOYEE_1));
		
		
	}
		
	@Test
	@Order(2)
	public void test_findAll () {
		List list = emplDao.findAllEmployees();
		assertThat(list).size().isGreaterThan(0);
	}
		
	@Test
	@Order(3)
	public void test_find () {
		int empId= emplDao.findEmployeeById(1).getEmpId();
		assertEquals(1,empId);
	}
		
//	@Test
//	@Order(4)
//	public void test_update () {
//		Empl EMPLOYEE_1= emplDao.findEmployeeById(1);
//		EMPLOYEE_1.setEmpLocation("Jabalpur");;
//		emplDao.updateEmployee(EMPLOYEE_1, 1);
//		assertEquals("Jabalpur", emplDao.findEmployeeById(1).getEmpLocation());
//	}
		
	@Test
	@Order(4)
	public void test_delete () {
		emplDao.deleteEmployee(1);
		Empl EMPLOYEE_1= emplDao.findEmployeeById(1);
		assertThat(EMPLOYEE_1).isNull();;

	}
}
