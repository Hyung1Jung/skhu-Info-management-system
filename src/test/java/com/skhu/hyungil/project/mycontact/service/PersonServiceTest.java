package com.skhu.hyungil.project.mycontact.service;

import com.skhu.hyungil.project.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    void getPeopleExcludeBlocks() {
        List<Person> result = personService.getPeopleExcludeBlock();

        result.forEach(System.out::println);

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("hyungil");
        assertThat(result.get(1).getName()).isEqualTo("yunggon");
        assertThat(result.get(2).getName()).isEqualTo("jinmin");


        // result.forEach(System.out::println); // 리스트에 각 객체가 한 줄씩 노츌

    }

    @Test
    void getPeopleByName() {
        List<Person> result = personService.getPeopleByName("hyungil");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("hyungil");
    }

    @Test
    void getPerson() {
        Person person = personService.getPerson(3L);

        assertThat(person.getName()).isEqualTo("gihyug");
    }
}