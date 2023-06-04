package com.delta.poc.svc;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.delta.poc.bo.EmplBo;
import com.delta.poc.excp.EmployeeNotFoundException;
import com.delta.poc.vo.Empl;
import com.fasterxml.jackson.databind.ObjectMapper;


@ComponentScan(basePackages="com.delta.poc")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {EmplSVCTest.class})
public class EmplSVCTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@Mock
	EmplBo emplBo;
	@InjectMocks
	EmplSVC emplSVC;
	List<Empl> listOfEmployees ;
	Empl empl;
	@BeforeEach
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(emplSVC).build();
	}

	@Test
	public void test_getAllRecords() throws Exception {
		List<Empl> listOfEmployees = new ArrayList<>();
		listOfEmployees.add(new Empl(1, "Priyanka", 23, "PAT", "Delhi"));
		listOfEmployees.add(new Empl(2, "Isha Singh", 20, "SD", "PUNE"));
		listOfEmployees.add(new Empl(3, "Swapnil Singh", 21, "SE", "Mumbai"));

		Mockito.when(emplBo.findAllEmployees()).thenReturn(listOfEmployees);
		this.mockMvc.perform(get("/delta/employees"))
		.andExpect(status().isOk())
		.andDo(print());

	}


	@Test
	public void test_findEmployeeById() throws Exception {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		int empId=1;
		Mockito.when(emplBo.findEmployeeById(EMPLOYEE_1.getEmpId())).thenReturn(EMPLOYEE_1);
		this.mockMvc.perform(get("/delta/employees/{id}",empId))
		.andExpect(status().isOk())
		.andDo(print());
	}


	@Test
	public void test_insertEmployee() throws Exception {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		

		Mockito.when(emplBo.insertEmployee(EMPLOYEE_1)).thenReturn("employee inserted successfully");

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/delta/employees/insert")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(EMPLOYEE_1));

		mockMvc.perform(mockRequest).andExpect(status().isCreated());
	}

	@Test
	public void test_updateEmployee() throws Exception {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		int empId=1;
		Mockito.when(emplBo.updateEmployee(EMPLOYEE_1.getEmpId(), EMPLOYEE_1 ))
				.thenReturn("employee updated successfully");
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/delta/employees/update/{id}",empId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(EMPLOYEE_1));
		mockMvc.perform(mockRequest).andExpect(status().isOk());
	}

	@Test
	public void test_deleteEmployee() throws Exception {
		Empl EMPLOYEE_1 = new Empl(1, "Riya Singh", 22, "TL", "Delhi");
		int empId=1;
		
		Mockito.when(emplBo.deleteEmployee(EMPLOYEE_1.getEmpId())).thenReturn("employee deleted successfully");

		mockMvc.perform(MockMvcRequestBuilders.delete("/delta/employees/delete/{id}",empId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
