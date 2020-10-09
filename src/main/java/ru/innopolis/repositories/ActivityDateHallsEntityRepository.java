package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.pojo.ActivityDateHallsEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
@Repository
public interface ActivityDateHallsEntityRepository extends JpaRepository<ActivityDateHallsEntity, Long> {
    List<ActivityDateHallsEntity> findAllByActivityDateBetween(Timestamp date, Timestamp date1);

    Optional<ActivityDateHallsEntity> findByActivityId(Long id);

    List<ActivityDateHallsEntity> findAllByActivityId(Long id);
    @Query(value = "select u.id from activity_date_halls u where u.activity_id=?1 and u.halls_id=?2 and activity_date=?3", nativeQuery = true)
    Optional<Long> findByActivityIdAndHallsIdAndActivityDate(Long activityId,Long hallsId,Timestamp activityDate);


}
