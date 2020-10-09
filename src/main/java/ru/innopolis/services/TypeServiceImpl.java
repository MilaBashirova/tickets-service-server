package ru.innopolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.dto.ActivityTypeDto;
import ru.innopolis.pojo.ActivityTypeEntity;
import ru.innopolis.repositories.ActivityTypeRepository;

import java.util.Optional;

import static ru.innopolis.dto.ActivityTypeDto.from;

/**
 * @author Bashirova Milyausha
 */
@Component
public class TypeServiceImpl implements TypeService {
    @Autowired
    ActivityTypeRepository activityTypeRepository;

    @Override
    public Optional<ActivityTypeDto> getActivityTypeByName(String name) {
        ActivityTypeEntity activityTypeEntity = activityTypeRepository.getActivityTypeEntityByActivityType(name);
        Optional<ActivityTypeDto> activityTypeDto = Optional.of(from(activityTypeEntity));
        return activityTypeDto;
    }

    @Override
    public ActivityTypeDto addActivityType(ActivityTypeDto activityTypeDto) {
        return null;
    }
}
