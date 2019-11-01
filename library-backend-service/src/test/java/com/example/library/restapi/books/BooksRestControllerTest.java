//package com.example.library.restapi.books;
//
//import com.example.library.app_service.LendingRecordsService;
//import com.example.library.domain.book.Book;
//import com.example.library.domain.lending.LendingRecord;
//import com.example.library.domain.user.User;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(BooksRestController.class)
//class BooksRestControllerTest {
//    @Autowired
//    private MockMvc mvc;
//    @MockBean
//    private LendingRecordsService service;
//
//    @DisplayName("検索する")
//    @Test
//    void test_01() throws Exception {
//        List<LendingRecord> lendingRecordList =
//                Arrays.asList(
////                        new LendingRecord(new Book("9784567890123", "title"), new User("1234567", "aa@bb")),
//                        new LendingRecord(new Book("9784567890124", "title"), new User("1234568", "aa@bb"))
//                );
//
//        when(service.search()).thenReturn(lendingRecordList);
//
//        mvc.perform(
//                get("/v1/books")
//        )
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"books\":[{\"isbn\":\"9784567890123\",\"userId\":\"1234567\"},{\"isbn\":\"9784567890124\",\"userId\":\"1234568\"}]}", true))
//        ;
//    }
//}