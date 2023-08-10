package com.example.springbatchrestartability.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class PersonEntity {

    @Id
    private int id;

    private String firstName;

    private String lastName;

    private int age;


}
