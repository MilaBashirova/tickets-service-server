package ru.innopolis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.ActivityDto;
import ru.innopolis.dto.UserDto;
import ru.innopolis.pojo.UsersEntity;
import ru.innopolis.services.UserService;

import java.util.List;
import java.util.Optional;

import static ru.innopolis.dto.UserDto.from;

/**
 * @author Helen Podolskaya
 */
@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        List<UserDto> usersList = userService.getAllUsers();
        return usersList;
    }

    @GetMapping("/user")
    public UserDto getUserByLoginPassword(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password) {
        UserDto user = userService.getUserByLoginPassword(email, password);
        return user;
    }

    @GetMapping("/userbyid")
    public UserDto getUserById(@RequestParam(value = "id", required = false) Long id) {
        UserDto user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/insertUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UsersEntity user = userService.addUser(userDto);
        UserDto resultAdd = from(user);
        return ResponseEntity.of(Optional.of(resultAdd));
    }

    @PostMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UsersEntity user = userService.saveUser(userDto);
        UserDto resultAdd = from(user);
        return ResponseEntity.of(Optional.of(resultAdd));
    }
}
