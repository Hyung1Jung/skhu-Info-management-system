package com.skhu.hyungil.project.mycontact.service;

import com.skhu.hyungil.project.mycontact.controller.dto.PersonDto;
import com.skhu.hyungil.project.mycontact.domain.Person;
import com.skhu.hyungil.project.mycontact.domain.dto.Birthday;
import com.skhu.hyungil.project.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 옵셔널 지원 <-> import javax.transaction.Transactional 지원 안 함

import java.util.List;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleExcludeBlock() {
//        List<Person> people = personRepository.findAll();
//        List<Block> blocks = blockRepository.findAll();
//        List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());

//        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());

        return personRepository.findByBlockIsNull();
    }

    public List<Person> getPeopleByName(String name) {
        //  List<Person> people = personRepository.findAll();
        //  return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());

        return personRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
        Person person = personRepository.findById(id).orElse(null);

        log.info("person : {}", person);

        return person;
    }

    @Transactional
    public void put(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person personAtDb = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));

        if (!personAtDb.getName().equals(personDto.getName())) {
            throw new RuntimeException("이름이 다릅니다.");
        }

        personAtDb.setName(personDto.getName());
        personAtDb.setPhoneNumber(personDto.getPhoneNumber());
        personAtDb.setJob(personDto.getJob());

        if(personDto.getBirthday() != null) {
            personAtDb.setBirthday(new Birthday(personDto.getBirthday()));
        }

        personAtDb.setAddress(personDto.getAddress());
        personAtDb.setBloodType(personDto.getBloodType());
        personAtDb.setHobby(personDto.getHobby());
        personAtDb.setAge(personDto.getAge());

        personRepository.save(personAtDb);
    }
}
