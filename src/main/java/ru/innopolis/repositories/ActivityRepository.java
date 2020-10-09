package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.pojo.ActivityEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
    List<ActivityEntity> findAllByTitle(String title);

    List<ActivityEntity> findAllByTypeId(long type);

    List<ActivityEntity> findAllByLocationIdIn(List<Long> locationId);

    List<ActivityEntity> findAllByIdIn(List<Long> activityId);

    List<ActivityEntity> findAllByTitleAndLocationIdIn(String title, List<Long> locationId);

    List<ActivityEntity> findAllByTypeIdAndLocationIdIn(Long typeId, List<Long> listLocationId);

    @Modifying()
    @Transactional()
    @Query(value = "UPDATE activity SET title = ?1,type_id=?2,location_id=?3,poster_id=?4 WHERE id = ?5", nativeQuery = true)
    void updateActivityById(String title, Long type_id, Long location_id, Long poster_id, Long actvityId);

    @Query(value = "select c.location_id from activity_location  c  where location_id in(select u.id from location u where u.city_id=?1 )", nativeQuery = true)
    List<Long> findAllByActivityLocationdIdIdByCity(Long city_id);

    @Query(value = "select* from activity c  where c.location_id in ?1 and  c.id in(select u.activity_id from activity_date_halls u where u.activity_date between ?2 and ?3  )", nativeQuery = true)
    List<ActivityEntity> findActivityEntityByDateAndLocationId(List<Long> idActivity, Timestamp date, Timestamp date1);

}
