package com.skhu.hyungil.project.mycontact.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skhu.hyungil.project.mycontact.controller.dto.PersonDto;
import com.skhu.hyungil.project.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
class PersonControllerTest {
    @Autowired
    private PersonController personController;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    PersonRepository personRepository;

    private MockMvc mockMvc;

    @BeforeEach // 해당 메소드는 매 test마다 먼저 실행이됨
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("hyungil"));
    }

    @Test
    void postPerson() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\n" +
                                "  \"name\": \"hyungil2\",\n" +
                                "  \"age\": 20,\n" +
                                "  \"bloodType\": \"A\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void modifyPerson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\n" +
                                "  \"name\": \"hyungil\"" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk());
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
    @Test
    void checkJsonString() throws JsonProcessingException{
        PersonDto dto = new PersonDto();
        dto.setName("hyungil");
        dto.setBirthday(LocalDate.now());
        dto.setAddress("안양");

        System.out.println(">>>" + toJsonString(dto));
    }

    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }
}