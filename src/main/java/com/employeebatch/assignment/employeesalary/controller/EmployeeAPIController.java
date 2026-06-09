package com.employeebatch.assignment.employeesalary.controller;

import com.employeebatch.assignment.employeesalary.model.Employee;
import com.employeebatch.assignment.employeesalary.service.EmployeeTasklet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeAPIController {

        EmployeeTasklet service;
        Employee employee;

        public EmployeeAPIController(EmployeeTasklet service) {
            this.service=service;
        }

        @GetMapping("")
        public List<Employee> getEmployeeDetails() {
            return service.listEmployees();
        }

       @GetMapping("/{id}")
        public Employee getEmployee(@PathVariable long id) {
            return service.readEmployee(id);
        }


        @PostMapping("")
        public String createEmployee(@RequestBody  Employee employee){
            return service.postemployee(employee);
        }

        @PutMapping("")
        public String updateEmployee(@RequestBody  Employee employee){
          return  service.updateEmployee(employee);
        }


        @DeleteMapping("{id}")
        public String deleteEmployee(@PathVariable long id){
            return service.deleteEmployee(id);
        }
}
