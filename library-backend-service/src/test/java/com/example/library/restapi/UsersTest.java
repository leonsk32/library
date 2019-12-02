package com.example.library.restapi;

import com.example.library.app_service.UserService;
import com.example.library.domain.user.User;
import com.example.library.restapi.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
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
                                "      \"familyName\": \"kirima\",\n" +
                                "      \"givenName\": \"nainai\"\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"userId\": \"1234568\",\n" +
                                "      \"email\": \"cc@bb\",\n" +
                                "      \"familyName\": \"ki\",\n" +
                                "      \"givenName\": \"na\"\n" +
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
                                "  \"familyName\": \"kirima\",\n" +
                                "  \"givenName\": \"nainai\"\n" +
                                "}", true));
        verify(userService).searchById("1234567");
    }

    @DisplayName("ユーザの新規作成")
    @Test
    void test03() throws Exception {
        // arrange

        UserDto userDto = new UserDto();
        userDto.setUserId("1234567");
        userDto.setEmail("kiri@kiri");
        userDto.setFamilyName("nainai");
        userDto.setGivenName("kirima");

        //language=json
        String body = "{\n" +
                "  \"userId\": \"1234567\",\n" +
                "  \"email\": \"kiri@kiri\",\n" +
                "  \"familyName\": \"nainai\",\n" +
                "  \"givenName\": \"kirima\"\n" +
                "}";

        // act
        doNothing().when(userService).register(userDto);
        mockMvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body))
                .andExpect(status().isOk());
        verify(userService).register(userDto);
    }
}