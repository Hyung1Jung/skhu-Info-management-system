package com.skhu.hyungil.project.mycontact.repository;

import com.skhu.hyungil.project.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired // 빈주입
    private PersonRepository personRepository;

    @Test // 해당 메소드가 테스트인 것 임을 표시
    void crud() {
        Person person = new Person();

        person.setName("형일");
        person.setAge(27);

        personRepository.save(person);

        // System.out.println(personRepository.findAll());

        List<Person> people = personRepository.findAll();


    }
}