package ru.innopolis.services;

import ru.innopolis.dto.UserDto;
import ru.innopolis.pojo.UsersEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Helen Podolskaya
 */

public interface UserService {
    UsersEntity saveUser(UserDto userDto);

    UsersEntity addUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserByLoginPassword(String userLogin, String userPassword);

    UserDto getUserById(Long userId);
}
