package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.pojo.CityEntity;

import java.util.List;

/**
 * @author Bashirova Milyausha
 * Helen Podolskaya
 */
@Repository
public interface CityRepository extends JpaRepository<CityEntity,Long> {
    CityEntity getCityEntityByCity(String city);
    CityEntity getCityEntityById(Long cityId);
    @Query(value = "select*from city", nativeQuery = true)
    List<CityEntity>  getAll();
}
