package com.example.library.restapi;

import com.example.library.app_service.LendingRecordsService;
import com.example.library.domain.book.Book;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LendingRecords.class)
class LendingRecordsTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LendingRecordsService service;

    @DisplayName("2件、検索する")
    @Test
    void test_01() throws Exception {
        List<LendingRecord> lendingRecordList = asList(
                new LendingRecord(new Book("9784567890123"), new User("1234567", "aa@bb")),
                new LendingRecord(new Book("9784567890124"), new User("1234568", "aa@bb", "mizukami", "hiroto")));

        when(service.searchForEvent()).thenReturn(lendingRecordList);

        mockMvc.perform(get("/v1/lendingRecords"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                                "  \"lendingRecords\": [\n" +
                                "    {\n" +
                                "      \"isbn\": \"9784567890123\",\n" +
                                "      \"userId\": \"1234567\",\n" +
                                "      \"namae\": null,\n" +
                                "      \"simei\": null\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"isbn\": \"9784567890124\",\n" +
                                "      \"userId\": \"1234568\",\n" +
                                "      \"namae\": \"hiroto\",\n" +
                                "      \"simei\": \"mizukami\" \n" +
                                "    }\n" +
                                "  ]\n" +
                                "}", true));
    }

    @DisplayName("０件検索する")
    @Test
    void test_02() throws Exception {
        List<LendingRecord> lendingRecordList = asList();

        when(service.searchForEvent()).thenReturn(lendingRecordList);

        mockMvc.perform(get("/v1/lendingRecords"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                                "  \"lendingRecords\": [\n" +
                                "  ]\n" +
                                "}", true));
    }

    @DisplayName("借りる")
    @Test
    void test_03() throws Exception {
        // arrange
        doNothing().when(service).borrow("isbn", "userId");

        //language=json
        String body = "{\n" +
                "  \"isbn\": \"987654321\",\n" +
                "  \"userId\": \"1234567\"\n" +
                "}";

        // act and assert
        mockMvc.perform(
                post("/v1/lendingRecords")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(body))
                .andExpect(status().isOk());
        verify(service).borrow(any(), any());
    }
}