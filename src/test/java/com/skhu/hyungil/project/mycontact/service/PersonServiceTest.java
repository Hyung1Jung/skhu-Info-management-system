package com.skhu.hyungil.project.mycontact.service;

import com.skhu.hyungil.project.mycontact.domain.Person;
import com.skhu.hyungil.project.mycontact.repository.PersonRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    // PersonRepository로 Mock를 만들어서 PersonService에 주입
    @InjectMocks // 테스트의 대상이 되는 클래스
    private PersonService personService;
    @Mock // 해당대상이 되는 클래스에서 오토와일드 하고 있는 것들
    private PersonRepository personRepository;

    @Test
    void getPeopleByName() {
        when(personRepository.findByName("hyungil"))  // when은 if와 같은 느낌
                .thenReturn(Lists.newArrayList(new Person("hyungil")));

        List<Person> result = personService.getPeopleByName("hyungil");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("hyungil");
    }

}