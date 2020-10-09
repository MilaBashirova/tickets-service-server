package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.pojo.ActivityTypeEntity;

/**
 * @author Bashirova Milyausha
 */
public interface ActivityTypeRepository extends JpaRepository<ActivityTypeEntity,Long> {
    ActivityTypeEntity getActivityTypeEntityByActivityType(String activityType);
}
