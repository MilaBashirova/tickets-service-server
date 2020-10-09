package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.pojo.LocationEntity;

import java.util.List;

/**
 * @author Bashirova Milyausha
 */
public interface LocationRepository extends JpaRepository<LocationEntity,Long> {
    List<LocationEntity> findAllByCityId(long cityId);

}
