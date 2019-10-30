package com.example.library.restapi;

import com.example.library.app_service.LendingRecordsService;
import com.example.library.domain.book.Book;
import com.example.library.domain.lending.LendingRecord;
import com.example.library.domain.user.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LendingRecords.class)
class LendingRecordsTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LendingRecordsService service;

    @Disabled
    @DisplayName("検索する")
    @Test
    void test_01() throws Exception {
        List<LendingRecord> lendingRecordList = asList(
                        new LendingRecord(new Book("9784567890123"), new User("1234567", "aa@bb")),
                        new LendingRecord(new Book("9784567890124"), new User("1234568", "aa@bb", "mizukami", "hiroto")));

        when(service.search()).thenReturn(lendingRecordList);

        mockMvc.perform(get("/tmp/rendingRecords"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                                "  \"lendingRecord\": [\n" +
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
}