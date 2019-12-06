package com.example.library.restapi;

import com.example.library.app_service.UserService;
import com.example.library.domain.user.User;
import com.example.library.restapi.dto.UserDto;
import com.example.library.restapi.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class Users implements UsersApi {

    private final UserService service;

    // FIXME 管理画面からのみのアクセスに変える
    // 外に晒すものでもないし、、、
    @CrossOrigin
    @Override
    public ResponseEntity<UsersDto> usersGet() {
        List<User> users = service.searchAll();
        return new ResponseEntity<>(convert(users), OK);
    }

    @CrossOrigin
    @Override
    public ResponseEntity<Void> usersPut(@RequestBody @Valid UserDto users) {
        service.register(toDomain(users));
        return new ResponseEntity<>(OK);
    }

    @CrossOrigin
    @Override
    public ResponseEntity<UserDto> usersUserIdGet(String userId) {
        User user = service.searchById(userId);
        return new ResponseEntity<>(toDto(user), OK);
    }

    @CrossOrigin
    @Override
    public ResponseEntity<Void> usersUserIdDelete(String userId) {
        service.delete(userId);
        return new ResponseEntity<>(OK);
    }

    private UsersDto convert(List<User> users) {
        UsersDto result = new UsersDto();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            userDtoList.add(toDto(user));
        }

        result.setUsers(userDtoList);
        return result;
    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());
        userDto.setFamilyName(user.getFamilyName());
        userDto.setGivenName(user.getGivenName());
        return userDto;
    }

    private UserDto toDomain(UserDto body) {
        UserDto userDto = new UserDto();
        userDto.setUserId(body.getUserId());
        userDto.setEmail(body.getEmail());
        userDto.setFamilyName(body.getFamilyName());
        userDto.setGivenName(body.getGivenName());
        return userDto;
    }
}
