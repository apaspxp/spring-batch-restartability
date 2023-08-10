package com.example.springbatchrestartability.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Person {

    private int id;

    private String firstName;

    private String lastName;

    private int age;



}
