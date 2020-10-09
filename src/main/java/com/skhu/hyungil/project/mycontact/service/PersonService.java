package com.skhu.hyungil.project.mycontact.service;

import com.skhu.hyungil.project.mycontact.domain.Block;
import com.skhu.hyungil.project.mycontact.domain.Person;
import com.skhu.hyungil.project.mycontact.repository.BlockRepository;
import com.skhu.hyungil.project.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleExcludeBlock() {
        List<Person> people = personRepository.findAll();
//        List<Block> blocks = blockRepository.findAll();
//        List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());

        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
    }

    public List<Person> getPeopleByName(String name) {
      //  List<Person> people = personRepository.findAll();
      //  return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());

        return personRepository.findByName(name);
    }

//    @Transactional(readOnly = true)
//    public Person getPerson(Long id) {
//        Person person = personRepository.findById(id).get();
//
//        log.info("person : {}", person);
//
//        return person;
//    }
}
