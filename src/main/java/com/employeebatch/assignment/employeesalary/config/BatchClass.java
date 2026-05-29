package com.employeebatch.assignment.employeesalary.config;

import com.employeebatch.assignment.employeesalary.service.EmployeeTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class BatchClass {

    @Bean
    public Job jobBean(
            JobRepository jobRepository,
            JobCompletionNotificationImp listener,
            Step employeeStep){

        return new JobBuilder(
                "job",
                jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(employeeStep)
                .build();
    }

    @Bean
    public Step employeeStep(
            JobRepository jobRepository,
            DataSourceTransactionManager transactionManager,
            EmployeeTasklet employeeTasklet){

        return new StepBuilder(
                "employeeStep",
                jobRepository)
                .tasklet(
                        employeeTasklet,
                        transactionManager
                )
                .build();
    }
}