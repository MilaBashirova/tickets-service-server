package ru.innopolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.dto.ActivityDateHallsDto;
import ru.innopolis.pojo.ActivityDateHallsEntity;
import ru.innopolis.repositories.ActivityDateHallsEntityRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.innopolis.dto.ActivityDateHallsDto.from;

/**
 * @author Bashirova Milyausha
 */
@Service
@Transactional
@Component
public class ActivityDateHallsEntityServiceImpl implements ActivityDateHallsEntityService {
    @Autowired
    ActivityDateHallsEntityRepository activityDateHallsEntityRepository;

    @Override
    public List<Long> getAllActivityIdByDate(Timestamp date, Timestamp date1) {
        List<ActivityDateHallsEntity> activityDateHallsEntities = activityDateHallsEntityRepository.findAllByActivityDateBetween(date, date1);
        List<Long> listActivityId = new ArrayList<>();
        activityDateHallsEntities.stream().map(ActivityDateHallsEntity::getActivityId).forEach(listActivityId::add);
        return listActivityId;
    }

    @Override
    public Optional<Long> getActivityIdByActivityId(Long activityId) {
        Optional<ActivityDateHallsEntity> activityDateHallsEntity = activityDateHallsEntityRepository.findByActivityId(activityId);
        if (activityDateHallsEntity.isPresent())
            return Optional.of(activityDateHallsEntity.get().getId());
        else
            return Optional.empty();
    }

    @Override
    public void deleteByActivityId(Long activityId) {
        Optional<ActivityDateHallsEntity> activityDateHallsEntity = activityDateHallsEntityRepository.findByActivityId(activityId);
        if (activityDateHallsEntity.isPresent()) {
            Long id = activityDateHallsEntity.get().getId();
            activityDateHallsEntityRepository.deleteById(id);
        }
    }
    @Override
    public List<ActivityDateHallsDto>  getActivityDateByActivityId(Long activityId) {
        List<ActivityDateHallsEntity> activityDateHallsEntities=activityDateHallsEntityRepository.findAllByActivityId(activityId);
        return from(activityDateHallsEntities);
    }

    @Override
    public Optional<Long> getActivityDateByActivityIdAndHallsIdAndActivityDate(Long activityId, Long hallsId, Timestamp activityDate) {
        Optional<Long> activityDateId=activityDateHallsEntityRepository.findByActivityIdAndHallsIdAndActivityDate(activityId,hallsId,activityDate);
            return activityDateId;

    }
}
