package com.employeebatch.assignment.employeesalary.dao;

import com.employeebatch.assignment.employeesalary.model.Employee;
import org.jooq.Record;
import org.jooq.Result;

import java.sql.Connection;
import java.util.ArrayList;

public interface update {
    ArrayList<Employee> list();
    void writeSummary(String dept,int avg,int max);
    String saveEmployee(Employee employee);
    String updateEmployee(Employee employee);
    String deleteEmployee(long id);
}
