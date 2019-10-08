package com.example.library.functionalTest;

import com.example.library.restapi.LibraryExceptionHandler;
import com.example.library.restapi.PersonDto;
import com.example.library.restapi.Persons;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PersonsTest {

    @Autowired
    private Persons persons;

    @Autowired
    ObjectMapper mapper;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(persons)
                .setControllerAdvice(new LibraryExceptionHandler())
                .build();
    }

    @DisplayName("人を登録する")
    @Test
    void test01() throws Exception {

        // arrange
        PersonDto personDto = new PersonDto();
        personDto.setPersonId("1234567");
        String request = mapper.writeValueAsString(personDto);

        // act and assert
        mvc.perform(put("/v1/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        ).andExpect(status().isOk());
    }
}