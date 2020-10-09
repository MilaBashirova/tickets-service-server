package ru.innopolis.dto;
/**
 * @author Podolskaya Helen
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.ActivityDateHallsEntity;
import ru.innopolis.pojo.ActivityEntity;
import ru.innopolis.pojo.HallsEntity;
import ru.innopolis.pojo.UsersEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ActivityDateHallsDto {
    private long id;
    private Timestamp activityDate;
    private Long hallsId;
    private long activityId;
    private HallsDto hallsByHallsId;
    private ActivityDto activityByActivityId;


    public static ActivityDateHallsDto from(ActivityDateHallsEntity activityDateHallsEntity) {
        return ActivityDateHallsDto.builder().id(activityDateHallsEntity.getId())
                .activityDate(activityDateHallsEntity.getActivityDate())
                .hallsId(activityDateHallsEntity.getHallsId())
                .activityId(activityDateHallsEntity.getActivityId())
                .hallsByHallsId(HallsDto.from(activityDateHallsEntity.getHallsByHallsId()))
                .activityByActivityId(ActivityDto.from(activityDateHallsEntity.getActivityByActivityId()))
                .build();
    }

    public static List<ActivityDateHallsDto> from(List<ActivityDateHallsEntity> activityDateHallsEntity) {
        return activityDateHallsEntity.stream().map(ActivityDateHallsDto::from).collect(Collectors.toList());
    }
}
