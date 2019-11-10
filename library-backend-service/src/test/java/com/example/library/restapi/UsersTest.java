package com.example.library.restapi;

import com.example.library.app_service.UserService;
import com.example.library.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(Users.class)
class UsersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @DisplayName("全検索")
    @Test
    void name() throws Exception {
        when(userService.searchAll()).thenReturn(List.of(
                new User("1234567", "aa@bb", "kirima", "nainai"),
                new User("1234568", "cc@bb", "ki", "na")
        ));
        mockMvc.perform(get("/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                                "  \"users\": [\n" +
                                "    {\n" +
                                "      \"userId\": \"1234567\",\n" +
                                "      \"email\": \"aa@bb\",\n" +
                                "      \"simei\": \"kirima\",\n" +
                                "      \"namae\": \"nainai\"\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"userId\": \"1234568\",\n" +
                                "      \"email\": \"cc@bb\",\n" +
                                "      \"simei\": \"ki\",\n" +
                                "      \"namae\": \"na\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}", true));
        verify(userService).searchAll();
    }

    @DisplayName("検索")
    @Test
    void test02() throws Exception {
        when(userService.searchById("1234567")).thenReturn(new User("1234567", "aa@bb", "kirima", "nainai"));
        mockMvc.perform(get("/v1/users/1234567"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\n" +
                                "  \"userId\": \"1234567\",\n" +
                                "  \"email\": \"aa@bb\",\n" +
                                "  \"simei\": \"kirima\",\n" +
                                "  \"namae\": \"nainai\"\n" +
                                "}", true));
        verify(userService).searchById("1234567");
    }

}