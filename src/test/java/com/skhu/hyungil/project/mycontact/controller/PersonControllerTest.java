package com.skhu.hyungil.project.mycontact.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skhu.hyungil.project.mycontact.controller.dto.PersonDto;
import com.skhu.hyungil.project.mycontact.domain.Person;
import com.skhu.hyungil.project.mycontact.domain.dto.Birthday;
import com.skhu.hyungil.project.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
class PersonControllerTest {
    private PersonController personController;
    private ObjectMapper objectMapper;
    private PersonRepository personRepository;
    private MappingJackson2HttpMessageConverter messageConverter;

    @Autowired
    public PersonControllerTest(PersonController personController, ObjectMapper objectMapper, PersonRepository personRepository, MappingJackson2HttpMessageConverter messageConverter){
        this.personController = personController;
        this.objectMapper = objectMapper;
        this.personRepository = personRepository;
        this.messageConverter = messageConverter;
    }



    private MockMvc mockMvc;

    @BeforeEach     // 해당 메소드는 매 test마다 먼저 실행이됨
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).setMessageConverters(messageConverter).build();
    }

    @Test
    void getPerson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("hyungil")) // $ 객체의미 // value 가져온 값에 대한 검증
                .andExpect(jsonPath("$.hobby").isEmpty())
                .andExpect(jsonPath("$.address").isEmpty())
                .andExpect(jsonPath("$.birthday").value("1994-10-10"))
                .andExpect(jsonPath("$.job").isEmpty())
                .andExpect(jsonPath("$.phoneNumber").isEmpty())
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(jsonPath("$.age").isNumber()) // 숫자의 값이 value를 가지고 있는지
                .andExpect(jsonPath("$.birthdayToday").isBoolean());
    }


    @Test
    void postPerson() throws Exception {
        PersonDto dto = PersonDto.of("hyungil", "programming", "안양", LocalDate.now(), "programmer", "010-1234-1234");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isCreated());

        Person result = personRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).get(0);

        assertAll(
                () -> assertThat(result.getName()).isEqualTo("hyungil"),
                () -> assertThat(result.getHobby()).isEqualTo("programming"),
                () -> assertThat(result.getAddress()).isEqualTo("안양"),
                () -> assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                () -> assertThat(result.getJob()).isEqualTo("programmer"),
                () -> assertThat(result.getPhoneNumber()).isEqualTo("010-1234-1234")
        );
    }

    @Test
    void modifyPerson() throws Exception {
        PersonDto dto = PersonDto.of("hyungil", "programming", "안양", LocalDate.now(), "programmer", "010-1234-1234");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk());

        Person result = personRepository.findById(1L).get();

        // 한 번의 검증으로 6개의 잘못된 것들 보기
        assertAll(
                () -> assertThat(result.getName()).isEqualTo("hyungil"),
                () -> assertThat(result.getHobby()).isEqualTo("programming"),
                () -> assertThat(result.getAddress()).isEqualTo("안양"),
                () -> assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                () -> assertThat(result.getJob()).isEqualTo("programmer"),
                () -> assertThat(result.getPhoneNumber()).isEqualTo("010-1234-1234")
        );
    }

    @Test
    void modifyPersonPersonIfNameIsDifferent() throws Exception {
        PersonDto dto = PersonDto.of("minsub", "programming", "안양", LocalDate.now(), "programmer", "010-1234-1234");

        assertThrows(NestedServletException.class, () ->
                mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/person/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(toJsonString(dto)))
                        .andDo(print())
                        .andExpect(status().isOk()));
    }


    @Test
    void modifyName() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                        .param("name", "hyungilModifed"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("hyungilModifed");


    }

    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());

        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId() == 1L));

    }

    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }
}