package com.employeebatch.assignment.employeesalary.service;

import com.employeebatch.assignment.employeesalary.dao.EmployeeDaoImpl;
import com.employeebatch.assignment.employeesalary.model.Employee;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;


@Service
public class EmployeeTasklet implements Tasklet {

    @Autowired
    EmployeeDaoImpl updatedata;

    private final DataSource dataSource;

    public EmployeeTasklet(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Connection connection = dataSource.getConnection();

        //Call to dao layer
    ResultSet result=updatedata.read(connection);

        // Employee list

        ArrayList<Employee> employees = new ArrayList<>();

        while(result.next()) {

            long id = result.getLong("id");

            String name = result.getString("name");

            String department = result.getString("department");

            int salary = result.getInt("salary");

            Employee emp = new Employee(
                            id,
                            name,
                            department,
                            salary );

            employees.add(emp);
        }

        // Department lists

        List<Employee> it = new ArrayList<>();
        List<Employee> hr = new ArrayList<>();
        List<Employee> marketing = new ArrayList<>();

        // Grouping

        for(Employee e : employees){

            if(e.getDepartment().equals("IT")){
                it.add(e);
            }

            else if(e.getDepartment().equals("HR")){
                hr.add(e);
            }

            else if(e.getDepartment()
                    .equals("Marketing")){

                marketing.add(e);
            }
        }

        // IT calculations

        int itsum = it.stream().map(emp -> emp.getSalary()).reduce(0,(a,b)->a+b);
        int itavg = itsum / it.size();
        int itmax = it.stream().map(emp -> emp.getSalary()).max(Integer::compare).get();

        // HR calculations

        int hrsum = hr.stream().map(emp -> emp.getSalary()).reduce(0,(a,b)->a+b);

        int hravg = hrsum / hr.size();

        int hrmax = hr.stream().map(emp -> emp.getSalary()).max(Integer::compare).get();

        // Marketing calculations

        int marketingsum = marketing.stream().map(emp -> emp.getSalary()).reduce(0,(a,b)->a+b);
        int marketingavg = marketingsum / marketing.size();
        int marketingmax = marketing.stream().map(emp -> emp.getSalary()).max(Integer::compare).get();

       //Writing call to dao layer
        updatedata.write(connection,"IT",itavg,itmax);
        updatedata.write(connection,"HR",hravg,hrmax);
        updatedata.write(connection,"MARKETING",marketingavg,marketingmax);


        result.close();
        connection.close();

        return RepeatStatus.FINISHED;
    }
}