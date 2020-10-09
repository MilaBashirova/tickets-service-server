package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.pojo.HallsEntity;
import ru.innopolis.pojo.UsersEntity;

import java.util.Optional;

/**
 * @author Helen Podolskaya
 */
@Repository
public interface HallRepository extends JpaRepository<HallsEntity, Long> {
    HallsEntity getHallsEntityById(Long id);

}
