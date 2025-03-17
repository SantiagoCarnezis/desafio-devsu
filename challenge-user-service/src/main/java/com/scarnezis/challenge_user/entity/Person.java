package com.scarnezis.challenge_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="client", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("P")
@Getter
@Setter
public class Person {

    @Id
    private String id;
    private String name;
    private boolean gender;
    private int age;
    private String direction;
    private String phone;
}
