package com.example.springbatchrestartability.BatchConfiguration;


import com.example.springbatchrestartability.JobNotificationListener.PersonJobNotificationJobListener;
import com.example.springbatchrestartability.itemProcessor.PersonItemProcessor;
import com.example.springbatchrestartability.itemReader.PersonItemReader;
import com.example.springbatchrestartability.itemWriter.PersonItemWriter;
import com.example.springbatchrestartability.model.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class BatchConfiguration {





//    @Autowired
//    private PersonCsvReadingTasklet personCsvReadingTasklet;

//    @Autowired
//    public PersonItemReader personItemReader;

    @Autowired
    PersonJobNotificationJobListener personJobNotificationJobListener;



    @Bean
    public Job importUserJob(JobRepository jobRepository,
                             PersonJobNotificationJobListener listener, Step step1) {
        return  new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }
    @Bean
    public ItemReader<FlatFileItemReader> reader(){
        return new PersonItemReader();
    }


    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager, PersonItemWriter writer) throws Exception {
        return new StepBuilder("step1", jobRepository)
                .<Person, Person> chunk(10, transactionManager)
                .reader(reader().read())
                .processor(processor())
                .writer(writer)
//                .faultTolerant()
//                .skip(Throwable.class)
//                .skipPolicy(new AlwaysSkipItemSkipPolicy())
                .build();
    }



}
