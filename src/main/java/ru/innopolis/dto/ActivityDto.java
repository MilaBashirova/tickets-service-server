package ru.innopolis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.ActivityEntity;
import ru.innopolis.pojo.ActivityTypeEntity;
import ru.innopolis.pojo.LocationEntity;
import ru.innopolis.pojo.PostersEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bashirova Milyausha
 * Helen Podolskaya
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ActivityDto {
    private long id;
    private String title;
    private ActivityTypeEntity activityTypeByTypeId;
    private LocationEntity locationByLocationId;
    private PostersEntity postersByPosterId;

   public static ActivityDto from(ActivityEntity activityEntity) {
        return ActivityDto.builder().id(activityEntity.getId())
                .title(activityEntity.getTitle())
                .postersByPosterId(activityEntity.getPostersByPosterId())
                .activityTypeByTypeId(activityEntity.getActivityTypeByTypeId())
                .locationByLocationId(activityEntity.getLocationByLocationId())
                .build();
    }

    public static List<ActivityDto> from(List<ActivityEntity> activityEntities) {
        return activityEntities.stream().map(ActivityDto::from).collect(Collectors.toList());
    }


}
