package ru.innopolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.pojo.ActivityLocationEntity;
import ru.innopolis.repositories.ActivityLocationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bashirova Milyausha
 */
@Component
public class ActivityLocationServiceImpl implements ActivityLocationService {
    @Autowired
    ActivityLocationRepository activityLocationRepository;
   /* @Override
    public List<ActivityLocationEntity> getAllActivityLocationByLocationId(List<Long> locationsId) {
        List<ActivityLocationEntity> locationEntities=activityLocationRepository.findAllByLocationIdIn(locationsId);
        return locationEntities;
    }*/

    @Override
    public List<Long> getAllActivityLocationIdByLocationId(List<Long> locationsId) {
        List<ActivityLocationEntity> locationEntities=activityLocationRepository.findAllByLocationIdIn(locationsId);
        List<Long> listActivityLocationId=new ArrayList<>();
        locationEntities.stream().map(ActivityLocationEntity::getId).forEach(listActivityLocationId::add);

        return listActivityLocationId ;
    }
}
