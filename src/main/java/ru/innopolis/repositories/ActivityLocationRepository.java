package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.pojo.ActivityLocationEntity;
import ru.innopolis.pojo.LocationEntity;

import java.util.List;

/**
 * @author Bashirova Milyausha
 */
public interface ActivityLocationRepository extends JpaRepository<ActivityLocationEntity,Long> {
    List<ActivityLocationEntity> findAllByLocationIdIn(List<Long> locationEntities);
}
