package com.employeebatch.assignment.employeesalary.model;

import org.springframework.stereotype.Component;

@Component
public class Summary {

    private String department;

    private int avgSalary;

    private int maxSalary;

    public Summary() {
    }

    public Summary(
            String department,
            int avgSalary,
            int maxSalary) {

        this.department = department;
        this.avgSalary = avgSalary;
        this.maxSalary = maxSalary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(int avgSalary) {
        this.avgSalary = avgSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }
}
