package com.employeebatch.assignment.employeesalary.model;

public class Employee {

    private long id;

    private String name;

    private String department;

    private Integer salary;

    public Employee(
            long id,
            String name,
            String department,
            Integer salary) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}