package com.employeebatch.assignment.employeesalary.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

public interface update {
   ResultSet read(Connection connection);
    void write(Connection connection,String dept,int avg,int max);
}
