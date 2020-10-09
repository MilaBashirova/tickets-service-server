package ru.innopolis.repositories;

import org.omg.CORBA.UserException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.dto.UserDto;
import ru.innopolis.pojo.ActivityEntity;
import ru.innopolis.pojo.CityEntity;
import ru.innopolis.pojo.UsersEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Helen Podolskaya
 */
@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity getUsersEntityById(Long id);

    UsersEntity getUsersEntityByEmailAndPassword(String email, String password);

    Optional<UsersEntity> findByEmail(String username);

    Boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update users  set first_name = ?1, last_name =?2, middle_name= ?3," +
            " phone = ?4, city_id = ?5 where id =?6", nativeQuery = true)
    void updateUserById(Optional<String> firstName, Optional<String> lastName, Optional<String> middleName,
                        Optional<String> phone, Long cityId, Long userId);
}
