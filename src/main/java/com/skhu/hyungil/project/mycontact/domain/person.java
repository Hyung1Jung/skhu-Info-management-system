package com.skhu.hyungil.project.mycontact.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class person {
    @Id
    @GeneratedValue

    private long id;

    private String name;

    private int age;

}
