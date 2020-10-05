package com.skhu.hyungil.project.mycontact.repository;

import com.skhu.hyungil.project.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired // 빈주입
    private PersonRepository personRepository;

    @Test
    void crud() {
        Person person = new Person();

        person.setName("형일");
        person.setAge(27);
        person.setBloodType("A");

        personRepository.save(person);

        System.out.println(personRepository.findAll());

        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("형일");
        assertThat(people.get(0).getAge()).isEqualTo(27);
        assertThat(people.get(0).getBloodType()).isEqualTo("A");
    }

    @Test
    void hashCodeEquals() {
        Person person1 = new Person("형일", 27, "A");
        Person person2 = new Person("형일", 27, "A");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

}