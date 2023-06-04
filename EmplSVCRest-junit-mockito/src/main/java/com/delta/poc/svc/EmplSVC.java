package com.delta.poc.svc;

import java.util.List;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.delta.poc.bo.EmplBo;
import com.delta.poc.excp.EmployeeExistException;
import com.delta.poc.excp.EmployeeNotFoundException;
import com.delta.poc.vo.Empl;
@RestController
//@RequestMapping("/delta/employees")
public class EmplSVC {
	@Autowired
	private EmplBo emplBo;
	
	@GetMapping("/delta/employees")
	public ResponseEntity<List<Empl>> findAllEmployees() throws EmployeeNotFoundException {
		return ResponseEntity.ok(emplBo.findAllEmployees());
	}

	@GetMapping(value = "/delta/employees/{id}", produces = { "application/json" })
	public ResponseEntity<Empl> findEmployeeById(@PathVariable("id") int empId) throws Exception {
		return ResponseEntity.ok(emplBo.findEmployeeById(empId));
	}

	@PostMapping("/delta/employees/insert")
	public ResponseEntity<Object> insertEmployee(@RequestBody @Valid Empl empl)
			throws EmployeeExistException, EmployeeNotFoundException{
		return new ResponseEntity<>(emplBo.insertEmployee(empl),HttpStatus.CREATED);
	}

	@PutMapping("/delta/employees/update/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable("id") int empId, @RequestBody @Valid Empl empl)
			throws EmployeeNotFoundException {
		return ResponseEntity.ok(emplBo.updateEmployee(empId, empl));
	}

	@DeleteMapping("/delta/employees/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int empId) throws EmployeeNotFoundException {
		return ResponseEntity.ok(emplBo.deleteEmployee(empId));

	}
	@GetMapping("/delta/employees/sort")
    public ResponseEntity<List<Empl>> sortEmployees() throws EmployeeNotFoundException {
        return ResponseEntity.ok(emplBo.sortEmployees());
    }

}
