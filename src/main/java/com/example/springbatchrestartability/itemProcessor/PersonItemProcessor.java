package com.example.springbatchrestartability.itemProcessor;

import com.example.springbatchrestartability.model.Person;
import com.example.springbatchrestartability.model.PersonEntity;
import com.example.springbatchrestartability.repository.PersonRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Autowired
    private PersonRepository personRepository;

    private Function<Person, Person> processFunction = item ->
            new Person(item.getId(), item.getFirstName().toUpperCase(), item.getLastName().toUpperCase(), item.getAge());

    @Override
    public Person process(Person item) throws Exception {
        return processFunction.apply(item);
}
}


