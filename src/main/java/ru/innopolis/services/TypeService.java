package ru.innopolis.services;

import ru.innopolis.dto.ActivityTypeDto;

import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
public interface TypeService {
    Optional<ActivityTypeDto> getActivityTypeByName(String name);

    ActivityTypeDto addActivityType(ActivityTypeDto activityTypeDto);

}
