package com.employeebatch.assignment.employeesalary.service;

import com.employeebatch.assignment.employeesalary.dao.EmployeeDaoImpl;
import com.employeebatch.assignment.employeesalary.model.Employee;
import org.jooq.DSLContext;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;


@Service
public class EmployeeTasklet implements Tasklet {

    @Autowired
    EmployeeDaoImpl repository;
    DSLContext dsl;

    ArrayList<Employee> employees=new ArrayList<>();

    private final DataSource dataSource;

    public EmployeeTasklet(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    //Display All employee method
    public List<Employee> listEmployees(){
        employees=repository.list();
        return employees;
    }

    //Read specific employee method
    public Employee readEmployee(long id){
        return repository.readEmployee(id);
    }

    //Post method
    public String postemployee(Employee employee){
       return repository.saveEmployee(employee);
    }

    //Put Method
    public String updateEmployee(Employee employee){
        return repository.updateEmployee(employee);
    }

    public String deleteEmployee(long id){
        return repository.deleteEmployee(id);
    }



    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        employees=repository.list();
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
        repository.writeSummary("IT",itavg,itmax);
        repository.writeSummary("HR",hravg,hrmax);
        repository.writeSummary("MARKETING",marketingavg,marketingmax);



        return RepeatStatus.FINISHED;
    }
}