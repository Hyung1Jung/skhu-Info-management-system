package com.skhu.hyungil.project.mycontact.repository;

import com.skhu.hyungil.project.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired // 빈주입
    private PersonRepository personRepository;

    @Test
    void crud() {
        Person person = new Person();

        person.setName("hosuck");
        person.setAge(27);
        person.setBloodType("A");

        personRepository.save(person);

        List<Person> result = personRepository.findByName("hosuck");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("hosuck");
        assertThat(result.get(0).getAge()).isEqualTo(27);
        assertThat(result.get(0).getBloodType()).isEqualTo("A");
    }

    @Test
    void findByBloodType() {

        List<Person> result = personRepository.findByBloodType("A");

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("hyungil");
        assertThat(result.get(1).getName()).isEqualTo("gihyug");
    }

    @Test
    void findByBirthdayBetween() {
        List<Person> result = personRepository.findByMonthOfBirthday(10);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("hyungil");
        assertThat(result.get(1).getName()).isEqualTo("jinmin");
    }
}