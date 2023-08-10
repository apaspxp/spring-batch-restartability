package com.example.springbatchrestartability.itemWriter;

import com.example.springbatchrestartability.model.Person;
import com.example.springbatchrestartability.model.PersonEntity;
import com.example.springbatchrestartability.repository.PersonRepository;
import lombok.Lombok;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PersonItemWriter implements ItemWriter<Person> {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        chunk.getItems().forEach(person -> {
            Optional<PersonEntity> existingPersonOpt = personRepository.findById(Long.valueOf(person.getId()));
            existingPersonOpt.ifPresent(existingPerson -> {
                boolean isUpdated =
                        !existingPerson.getFirstName().equals(person.getFirstName()) ||
                                !existingPerson.getLastName().equals(person.getLastName()) ||
                                existingPerson.getAge() != person.getAge();

                if (isUpdated) {
                    // Update the existing person with the new data
                    existingPerson.setFirstName(person.getFirstName());
                    existingPerson.setLastName(person.getLastName());
                    existingPerson.setAge(person.getAge());
                    personRepository.save(existingPerson);
                    System.out.println("Updated: " + existingPerson);
                }
            });

            if (existingPersonOpt.isEmpty()) {
                // Person doesn't exist in the database, create a new entry
                PersonEntity newPerson = new PersonEntity(person.getId(), person.getFirstName(), person.getLastName(), person.getAge());
                personRepository.save(newPerson);
                System.out.println("Added: " + newPerson);
            }
        });
    }
}
