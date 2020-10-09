package ru.innopolis.services;

import ru.innopolis.dto.ActivityDto;
import ru.innopolis.pojo.ActivityEntity;
import ru.innopolis.pojo.ActivityLocationEntity;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
public interface ActivityService {
    List<ActivityEntity> getActivitiesById(List<Long> listActivityId);
    ActivityEntity addActivity(ActivityDto activityDto);

    Optional<ActivityDto> updateActivity(ActivityDto activityDto);

    void deleteActivity(ActivityDto activityDto);

    List<ActivityDto> findAllActivityByCity(String city);

    List<ActivityDto> findAllActivityByType(String type);


    List<ActivityDto> findAllActivityByTitle(String title);


    List<ActivityDto> findAllActivityByDate(Date date1);

    List<ActivityDto> findAllActivityByPeriod(Date dateStart, Date dateEnd);

    List<ActivityDto> findAllActivityByDateAndCity(Date date, String city);

    List<ActivityDto> findAllActivityByPeriodAndCity(Date dateStart1, Date dateEnd1, String city);

    List<ActivityDto> findAllActivityByTitleAndCity(String title, String city);

    List<ActivityDto> findAllActivityByCityAndType(String city, String type);

}
