package com.example.springbatchrestartability.repository;

import com.example.springbatchrestartability.model.Person;
import com.example.springbatchrestartability.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {
//    Optional<Person> findById(int id);
}
