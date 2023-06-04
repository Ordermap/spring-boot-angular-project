package com.delta.poc.svc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delta.poc.utils.EmployeeMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delta.poc.dao.EmplDaoIfc;
import com.delta.poc.excp.ResourceNotFoundException;
//import com.delta.poc.utils.KafkaProducerService;
import com.delta.poc.vo.Empl;
@RestController
@RequestMapping("/api/v1/")
public class EmplSVC {
   @Autowired
    private EmplDaoIfc emplDaoIfc;
    @Autowired
    private EmployeeMessageProducer employeeMessageProducer;

    @GetMapping("/employees")
    public List<Empl> getAllEmployees() {
        return emplDaoIfc.findAll();
    }

    // create employee rest api
    @PostMapping("/employees")
    public Empl createEmployee(@RequestBody Empl empl) {
       Empl emplCreated= emplDaoIfc.save(empl);
        employeeMessageProducer.sendMessage("Employee created:",emplCreated);
        return emplCreated ;
    }

    // get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Empl> getEmployeeById(@PathVariable Long id) {
        Empl empl = emplDaoIfc.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(empl);
    }

    // update employee rest api

    @PutMapping("/employees/{id}")
    public ResponseEntity<Empl> updateEmployee(@PathVariable Long id, @RequestBody Empl emplDetails) {
        Empl empl = emplDaoIfc.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        empl.setFirstName(emplDetails.getFirstName());
        empl.setLastName(emplDetails.getLastName());
        empl.setEmailId(emplDetails.getEmailId());

        Empl updatedEmployee = emplDaoIfc.save(empl);

        employeeMessageProducer.sendMessage("Employee updated: ",updatedEmployee);

        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        
		Empl empl = emplDaoIfc.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
                emplDaoIfc.delete(empl);
        //Empl deleteEmployee=empl;
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
        employeeMessageProducer.sendMessage("Employee deleted: ",empl);
		return ResponseEntity.ok(response);
	}

}
