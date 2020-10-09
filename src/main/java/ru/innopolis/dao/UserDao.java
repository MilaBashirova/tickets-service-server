package ru.innopolis.dao;

import ru.innopolis.dto.UserDto;

import java.util.List;

/**
 * @author Helen Podolskaya
 */
public interface UserDao {
    public void saveUser(ru.innopolis.pojo.UsersEntity user);
    public List<UserDto> getUsers();
}
