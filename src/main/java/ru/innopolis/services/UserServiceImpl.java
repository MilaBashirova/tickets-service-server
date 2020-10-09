package ru.innopolis.services;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.dao.UserDao;
import ru.innopolis.dao.UserDaoImpl;
import ru.innopolis.dto.CityDto;
import ru.innopolis.dto.UserDto;
import ru.innopolis.pojo.ActivityEntity;
import ru.innopolis.pojo.UsersEntity;
import ru.innopolis.repositories.CityRepository;
import ru.innopolis.repositories.UserRepository;
import ru.innopolis.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

import static ru.innopolis.dto.UserDto.from;

/**
 * @author Helen Podolskaya
 */
@Service
@Transactional
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CityRepository cityRepository;

    @Override
    public UsersEntity saveUser(UserDto userDto) {
       userRepository.updateUserById(Optional.of(userDto.getFirstName()),Optional.of(userDto.getLastName()),Optional.of(userDto.getMiddleName()),
               Optional.of(userDto.getPhone()),userDto.getCity().getId(),userDto.getId());
       return userRepository.getUsersEntityById(userDto.getId());
    }



    @Override
    public UsersEntity addUser(UserDto userDto) {
        UsersEntity userEntity = UsersEntity.fromUserDto(userDto);
        UsersEntity entity = userRepository.save(userEntity);
        return entity;
    }

    @Override
    public List<UserDto> getAllUsers() {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUsers();
    }

    @Override
    public UserDto getUserByLoginPassword(String userLogin, String userPassword) {
        UserDto user = from(userRepository.getUsersEntityByEmailAndPassword(userLogin, userPassword));
        return user;
    }

    @Override
    public UserDto getUserById(Long userId) {
        UserDto user = from(userRepository.getUsersEntityById(userId));
        return user;
    }
}
