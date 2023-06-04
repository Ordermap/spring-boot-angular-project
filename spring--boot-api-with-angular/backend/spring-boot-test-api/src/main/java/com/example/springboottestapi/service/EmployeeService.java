package com.example.springboottestapi.service;

import com.example.springboottestapi.exception.EmployeeNotFound;
import com.example.springboottestapi.model.Employee;
import com.example.springboottestapi.payload.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private List<Employee> employeeList=new ArrayList<>();

    public Employee addEmployee(String name){
        UUID uuid = UUID.randomUUID();

        Employee employee=new Employee();
        employee.setId(uuid.toString());
        employee.setName(name);
        employee.setImageUrl("https://picsum.photos/seed/"+uuid.toString()+"/200/300");
        employeeList.add(employee);
        return employee;
    }

    public Employee addEmployee(EmployeeRequest request){
        UUID uuid = UUID.randomUUID();

        Employee employee=new Employee();
        employee.setId(uuid.toString());
        employee.setName(request.getName());
        employee.setImageUrl("https://picsum.photos/seed/"+uuid.toString()+"/200/300");
        employeeList.add(employee);
        return employee;
    }
    public void removeEmployee(String uuid){
        for (Employee employee: employeeList){
            if(employee.getId().equals(uuid)){
                employeeList.remove(employee);
                return;
            }
        }
        throw new EmployeeNotFound("employee not found with uuid:"+uuid);
    }

    public List<Employee> getAllEmployee(){
        return employeeList;
    }
}
