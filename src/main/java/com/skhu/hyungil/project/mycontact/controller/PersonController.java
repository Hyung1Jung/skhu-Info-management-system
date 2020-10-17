package com.skhu.hyungil.project.mycontact.controller;

import com.skhu.hyungil.project.mycontact.controller.dto.PersonDto;
import com.skhu.hyungil.project.mycontact.domain.Person;
import com.skhu.hyungil.project.mycontact.repository.PersonRepository;
import com.skhu.hyungil.project.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 성공한 경우 201로 리턴
    public void postPerson(@RequestBody Person person) {
        personService.put(person);

        log.info("person -> {} ", personRepository.findAll());
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        personService.modify(id, personDto);

        log.info("person -> {} ", personRepository.findAll());
    }

    @PatchMapping("/{id}") // 일부 리소스만 update
    public void modifyPerson(@PathVariable Long id, String name) {
        personService.modify(id, name);

        log.info("person -> {} ", personRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);

        log.info("person -> {} ", personRepository.findAll());
    }




}