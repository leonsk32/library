package com.example.library.restapi;

import com.example.library.service.BookshelfService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = LibraryController.class)
class LibraryControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  BookshelfService service;

  @DisplayName("本の貸し出し")
  @Nested
  class borrow {
    @DisplayName("１冊の本を借りる")
    @Test
    void test01() throws Exception {

      // arrange
      final String isbn10 = "1111111111";

      // act and assert
      mvc.perform(patch("/v1/books/" + isbn10))
          .andExpect(status().isOk());
      verify(service).borrow(isbn10);
    }
  }
}
