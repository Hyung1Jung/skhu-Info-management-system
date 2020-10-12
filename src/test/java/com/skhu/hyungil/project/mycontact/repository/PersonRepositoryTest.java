package com.skhu.hyungil.project.mycontact.repository;

import com.skhu.hyungil.project.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
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

        List<Person> people = personRepository.findByName("hosuck");

        assertThat(people.size()).isEqualTo(2);
        assertThat(people.get(0).getName()).isEqualTo("hosuck");
        assertThat(people.get(0).getAge()).isEqualTo(26);
        assertThat(people.get(0).getBloodType()).isEqualTo("AB");

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