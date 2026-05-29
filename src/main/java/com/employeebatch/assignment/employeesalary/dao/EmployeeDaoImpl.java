package com.employeebatch.assignment.employeesalary.dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class EmployeeDaoImpl implements update {


    public ResultSet read(Connection connection) {
        // Read employee data
        System.out.println("in read block"+connection);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee");
            ResultSet result = preparedStatement.executeQuery();
            System.out.println(result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void write(Connection connection, String dept, int avg, int max) {
        try {
            System.out.println("in the write"+connection);
            String sql =
                    "INSERT INTO summary " +
                            "(department, avg_salary, max_salary) " +
                            "VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1, dept);
            insertStatement.setInt(2, avg);
            insertStatement.setInt(3, max);
            insertStatement.executeUpdate();
            System.out.println( " row inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
