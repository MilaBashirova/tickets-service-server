package ru.innopolis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.ActivityTypeEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bashirova Milyausha
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ActivityTypeDto {
    private long id;
    private String name;
    public static ActivityTypeDto from(ActivityTypeEntity activityTypeEntity) {
        return ActivityTypeDto.builder().id(activityTypeEntity.getId()).name(activityTypeEntity.getActivityType())
                .build();
    }

    public static List<ActivityTypeDto> from(List<ActivityTypeEntity> postersEntities) {
        return postersEntities.stream().map(ActivityTypeDto::from).collect(Collectors.toList());
    }
}
