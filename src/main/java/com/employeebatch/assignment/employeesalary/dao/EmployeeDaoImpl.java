package com.employeebatch.assignment.employeesalary.dao;

import static com.employeebatch.jooq.tables.Employee.EMPLOYEE;
import static com.employeebatch.jooq.tables.Summary.SUMMARY;

import com.employeebatch.assignment.employeesalary.model.Employee;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import org.jooq.Record;
import org.jooq.Result;

import java.util.ArrayList;

@Repository
public class EmployeeDaoImpl implements update {

    private final DSLContext dsl;

    public EmployeeDaoImpl(DSLContext dsl) {
        this.dsl = dsl;
    }


    public ArrayList<Employee> list() {

        Result<Record> result =
                dsl.select()
                        .from(EMPLOYEE)
                        .fetch();

        ArrayList<Employee> employees = new ArrayList<>();

        for(Record r:result){
            long id = r.get(EMPLOYEE.ID);
            String name = r.get(EMPLOYEE.NAME);
            String department = r.get(EMPLOYEE.DEPARTMENT);
            int salary = r.get(EMPLOYEE.SALARY);
            Employee emp = new Employee(id, name, department, salary );
            employees.add(emp);
        }

        return employees;
    }

    public Employee readEmployee(long id) {

        var result =
                dsl.select()
                        .from(EMPLOYEE)
                        .where(EMPLOYEE.ID.eq(id))
                        .fetchOneInto(Employee.class);

        return result;
    }



    public void writeSummary(String dept, int avg, int max) {

            dsl.insertInto(SUMMARY)
                    .set(SUMMARY.DEPARTMENT, dept)
                    .set(SUMMARY.AVG_SALARY, avg)
                    .set(SUMMARY.MAX_SALARY, max)
                    .execute();
        }

     public String saveEmployee(Employee employee) {

         dsl.insertInto(EMPLOYEE)
                 .set(EMPLOYEE.ID,employee.getId())
                 .set(EMPLOYEE.NAME,employee.getName())
                 .set(EMPLOYEE.SALARY, employee.getSalary())
                 .set(EMPLOYEE.DEPARTMENT,employee.getDepartment())
                 .execute();

        return "employee inserted Successfully";
    }

        public String updateEmployee(Employee employee){

            dsl.update(EMPLOYEE)
                    .set(EMPLOYEE.NAME,employee.getName())
                    .set(EMPLOYEE.SALARY, employee.getSalary())
                    .set(EMPLOYEE.DEPARTMENT,employee.getDepartment())
                    .where(EMPLOYEE.ID.eq(employee.getId()))
                    .execute();
            return "Employee Updated Successfully";
        }

    public String deleteEmployee(long id) {

        dsl.deleteFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .execute();

        return "Employee Deleted Successfully";
    }


}

