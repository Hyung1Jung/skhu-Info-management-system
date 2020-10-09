package com.skhu.hyungil.project.mycontact.repository;

import com.skhu.hyungil.project.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);

}
