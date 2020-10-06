package com.skhu.hyungil.project.mycontact.service;

import com.skhu.hyungil.project.mycontact.domain.Block;
import com.skhu.hyungil.project.mycontact.domain.Person;
import com.skhu.hyungil.project.mycontact.repository.BlockRepository;
import com.skhu.hyungil.project.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks() {
        givenPeople();
        givenBlocks();

        List<Person> result = personService.getPeopleExcludeBlock();

        // System.out.println(result);
        result.forEach(System.out::println); // 리스트에 각 객체가 한 줄씩 노츌

    }

    private void givenBlocks() {
        givenBlock("형일");
    }

    private Block givenBlock(String name) {
        return blockRepository.save(new Block(name));
    }

    private void givenBlockPerson(String name, int age, String bloodType) {
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(givenBlock(name));

        personRepository.save(blockPerson);
    }

    private void givenPeople() {
        givenBlockPerson("형일", 27, "A");
        givenPerson("영곤", 27, "B");
        givenPerson("기혁", 26, "o");
        givenPerson("일권", 26, "AB");
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }

}