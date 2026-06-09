package com.employeebatch.assignment.employeesalary.dao;

import com.employeebatch.assignment.employeesalary.model.Employee;
import org.jooq.*;

import org.jooq.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.employeebatch.jooq.tables.Employee.EMPLOYEE;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeDaoImplTest {

    @Mock
    DSLContext dsl;

    @Mock
    Result<Record> mockResult;

    @Mock
    SelectSelectStep<Record> selectStep;

    @Mock
    SelectJoinStep<Record> selectJoinStep;

    @Mock
    Record mockRecord;

    @InjectMocks
    EmployeeDaoImpl dao;

    @Test
    void testRead() {
        when(dsl.select())
                .thenReturn(selectStep);
        when(selectStep.from(EMPLOYEE))
                .thenReturn(selectJoinStep);
        when(selectJoinStep.fetch())
                .thenReturn(mockResult);

        when(mockResult.iterator())
                .thenReturn(List.of(mockRecord).iterator());


        when(mockRecord.get(EMPLOYEE.ID))
                .thenReturn(1L);
        when(mockRecord.get(EMPLOYEE.NAME))
                .thenReturn("Thanish");
        when(mockRecord.get(EMPLOYEE.DEPARTMENT))
                .thenReturn("IT");
        when(mockRecord.get(EMPLOYEE.SALARY))
                .thenReturn(50000);

        // call method
        ArrayList<Employee> employees = dao.list();

        //Assertions
        Assertions.assertEquals(1, employees.size());
        Assertions.assertEquals("Thanish", employees.get(0).getName());
        Assertions.assertEquals(50000, employees.get(0).getSalary());

        // verify
        verify(dsl).select();
    }

    @Test
    void testWrite(){

    }
}
