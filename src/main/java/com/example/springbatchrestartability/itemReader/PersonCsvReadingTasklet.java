//package com.example.springbatchrestartability.itemReader;
//
//import com.example.springbatchrestartability.model.Person;
//import com.example.springbatchrestartability.model.PersonEntity;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class PersonCsvReadingTasklet implements Tasklet {
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        var flatFileItemReader = new FlatFileItemReaderBuilder<PersonEntity>()
//                .name("personItemReader")
//                .resource(new ClassPathResource("sample-data.csv"))
//                .delimited()
//                .names(new String[]{"id", "firstName", "lastName", "age"})
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//                    setTargetType(PersonEntity.class);
//                }})
//                .build();
//        return RepeatStatus.FINISHED;
//    }
//
//}
//
//
//
