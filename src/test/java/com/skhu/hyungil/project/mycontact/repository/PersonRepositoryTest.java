package com.skhu.hyungil.project.mycontact.repository;

import com.skhu.hyungil.project.mycontact.domain.Person;
import jdk.vm.ci.meta.Local;
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

    @Test
    void findByBloodType() {
        givenPerson("형일", 27, "A");
        givenPerson("영곤", 27, "B");
        givenPerson("기혁", 26, "o");
        givenPerson("일권", 26, "AB");
        givenPerson("호석", 25, "A");
        givenPerson("준성", 25, "A");

        List<Person> result = personRepository.findByBloodType("A");

        result.forEach((System.out::println));
    }

    @Test
    void findByBirthdayBetween() {
        givenPerson("형일", 27, "A", LocalDate.of(1994, 10, 10));
        givenPerson("영곤", 27, "B", LocalDate.of(1994, 1, 1));
        givenPerson("기혁", 26, "o", LocalDate.of(1995, 2, 2));
        givenPerson("일권", 26, "AB", LocalDate.of(1995, 3, 3));
        givenPerson("호석", 25, "A", LocalDate.of(1998, 10, 4));

        List<Person> result = personRepository.findByBirthdayBetween(LocalDate.of(1994, 10, 1), LocalDate.of(1998, 10, 31));

        result.forEach(System.out::println);
    }
    // findByBloodType()에 생일 값이 없기 때문에 메소드 오버로딩 해준다.
    private void givenPerson(String name, int age, String bloodType) {
        givenPerson(name, age, bloodType, null);
    }

    private void givenPerson(String name, int age, String bloodType, LocalDate birthday) {
        Person person = new Person(name, age, bloodType);
        person.setBirthday(birthday);

        personRepository.save(person);

    }

}