package ru.innopolis.services;

import org.springframework.core.annotation.MergedAnnotations;
import ru.innopolis.dto.ActivityDateHallsDto;
import ru.innopolis.pojo.ActivityDateHallsEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
public interface ActivityDateHallsEntityService {
    List<Long> getAllActivityIdByDate(Timestamp date, Timestamp date1);

    Optional<Long> getActivityIdByActivityId(Long activityId);

    void deleteByActivityId(Long activityId);

    List<ActivityDateHallsDto> getActivityDateByActivityId(Long activityId);

    Optional<Long> getActivityDateByActivityIdAndHallsIdAndActivityDate(Long activityId, Long hallsId, Timestamp activityDate);
}
