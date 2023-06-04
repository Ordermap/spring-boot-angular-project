package com.example.springboottestapi;

import com.example.springboottestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class SpringBootTestApiApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestApiApplication.class, args);

    }

    @Autowired
    EmployeeService employeeService;
    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            employeeService.addEmployee("employee"+i);

        }
    }
}
