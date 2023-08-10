package com.example.springbatchrestartability.JobNotificationListener;

import com.example.springbatchrestartability.model.Person;
import com.example.springbatchrestartability.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PersonJobNotificationJobListener implements JobExecutionListener {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            personRepository.findAll().stream().forEach(System.out::println);
        }
    }
}
