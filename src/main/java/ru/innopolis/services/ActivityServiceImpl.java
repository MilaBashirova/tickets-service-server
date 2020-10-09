package ru.innopolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.dto.ActivityDto;
import ru.innopolis.dto.ActivityTypeDto;
import ru.innopolis.dto.CityDto;
import ru.innopolis.pojo.ActivityEntity;
import ru.innopolis.repositories.ActivityRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.innopolis.dto.ActivityDto.from;

/**
 * @author Bashirova Milyausha
 */
@Component
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    TypeService typeService;

    @Autowired
    PosterService posterService;

    @Autowired
    CityService cityService;

    @Autowired
    LocationService locationService;

    @Autowired
    ActivityLocationService activityLocationService;

    @Autowired
    ActivityDateHallsEntityService activityDateHallsEntityService;

    @Override
    public List<ActivityEntity> getActivitiesById(List<Long> listActivityId) {
        List<ActivityEntity> activityEntities = activityRepository.findAllByIdIn(listActivityId);
        return activityEntities;
    }

    @Override
    public ActivityEntity addActivity(ActivityDto activityDto) {
        ActivityEntity activityEntity = ActivityEntity.fromActivityDto(activityDto);
        ActivityEntity entity = activityRepository.save(activityEntity);
        return entity;
    }

    @Override
    public Optional<ActivityDto> updateActivity(ActivityDto activityDto) {
        activityRepository.updateActivityById(activityDto.getTitle(),activityDto.getActivityTypeByTypeId().getId(),
                activityDto.getLocationByLocationId().getId(),
                activityDto.getPostersByPosterId().getId(),activityDto.getId());
        return Optional.empty();
    }

    @Override
    public void deleteActivity(ActivityDto activityDto) {
        activityDateHallsEntityService.deleteByActivityId(activityDto.getId());
        activityRepository.deleteById(activityDto.getId());
    }

    @Override
    public List<ActivityDto> findAllActivityByTitle(String title) {
        List<ActivityEntity> activityEntities = activityRepository.findAllByTitle(title);
        return from(activityEntities);
    }
    @Override
    public List<ActivityDto> findAllActivityByCity(String city) {
        Optional<CityDto> cityDto = cityService.getCityByName(city);
        List<ActivityEntity> activityEntities = null;
        if (cityDto.isPresent()) {
            List<Long> listLocationsId = locationService.getLocationsIdByCityId(cityDto.get().getId());
            List<Long> activityLocationEntities = activityLocationService.getAllActivityLocationIdByLocationId(listLocationsId);
            activityEntities = activityRepository.findAllByLocationIdIn(activityLocationEntities);
            List<ActivityDto> activityDtos = from(activityEntities);
            return activityDtos;
        } else
            return null;
    }

    @Override
    public List<ActivityDto> findAllActivityByType(String type) {
        Optional<ActivityTypeDto> activityTypeDto = typeService.getActivityTypeByName(type);
        List<ActivityEntity> activityEntities = null;
        if (activityTypeDto.isPresent()) {
            activityEntities = activityRepository.findAllByTypeId(activityTypeDto.get().getId());
            List<ActivityDto> activityDtos = from(activityEntities);
            return activityDtos;
        } else
            return null;
    }


    @Override
    public List<ActivityDto> findAllActivityByDate(Date date1) {
        List<Timestamp> timestampList=getTimestampByDate(date1,date1);
        List<Long> listActivityId = activityDateHallsEntityService.getAllActivityIdByDate(timestampList.get(0), timestampList.get(1));
        List<ActivityEntity> activityEntities = getActivitiesById(listActivityId);
        List<ActivityDto> activityDtos = from(activityEntities);
        return activityDtos;
    }

    @Override
    public List<ActivityDto> findAllActivityByPeriod(Date dateStart, Date dateEnd) {
        List<Timestamp> timestampList=getTimestampByDate(dateStart,dateEnd);

        List<Long> listActivityId = activityDateHallsEntityService.getAllActivityIdByDate(timestampList.get(0), timestampList.get(1));
        List<ActivityEntity> activityEntities = getActivitiesById(listActivityId);
        List<ActivityDto> activityDtos = from(activityEntities);
        return activityDtos;
    }


    @Override
    public List<ActivityDto> findAllActivityByDateAndCity(Date date, String city) {
        List<Timestamp> timestampList=getTimestampByDate(date,date);
        Optional<CityDto> cityDto = cityService.getCityByName(city);
        List<Long> listLocationId = activityRepository.findAllByActivityLocationdIdIdByCity(cityDto.get().getId());
        List<ActivityEntity> activityEntities = activityRepository.findActivityEntityByDateAndLocationId(listLocationId,timestampList.get(0),timestampList.get(1));
        return from(activityEntities);
    }


    @Override
    public List<ActivityDto> findAllActivityByPeriodAndCity(Date dateStart, Date dateEnd, String city) {
        List<Timestamp> timestampList=getTimestampByDate(dateStart,dateEnd);
        Optional<CityDto> cityDto = cityService.getCityByName(city);
        List<Long> listLocationId = activityRepository.findAllByActivityLocationdIdIdByCity(cityDto.get().getId());
        List<ActivityEntity> activityEntities = activityRepository.findActivityEntityByDateAndLocationId(listLocationId,timestampList.get(0),timestampList.get(1));
        return from(activityEntities);
    }

    @Override
    public List<ActivityDto> findAllActivityByTitleAndCity(String title, String city) {
        Optional<CityDto> cityDto = cityService.getCityByName(city);
        if (cityDto.isPresent()) {
            List<Long> listLocationId = locationService.getLocationsIdByCityId(cityDto.get().getId());
            List<ActivityEntity> activityEntities = activityRepository.findAllByTitleAndLocationIdIn(title, listLocationId);
            return from(activityEntities);
        } else
            return null;
    }

    @Override
    public List<ActivityDto> findAllActivityByCityAndType(String city, String type) {
        Optional<ActivityTypeDto> typeDto = typeService.getActivityTypeByName(type);
        Optional<CityDto> cityDto = cityService.getCityByName(city);
        if (cityDto.isPresent() && typeDto.isPresent()) {
            List<Long> listLocationId = locationService.getLocationsIdByCityId(cityDto.get().getId());
            List<ActivityEntity> activityEntities = activityRepository.findAllByTypeIdAndLocationIdIn(typeDto.get().getId(), listLocationId);
            return from(activityEntities);

        } else
            return null;
    }

    private List<Timestamp> getTimestampByDate(Date dateStart,Date dateEnd){
        Timestamp timestamp = new Timestamp(dateStart.getTime());
        String str = dateEnd.toString() + " 23:59:59.0";
        Timestamp timestamp1 = Timestamp.valueOf(str);
        List<Timestamp> timestampList=new ArrayList<>();
        timestampList.add(timestamp);
        timestampList.add(timestamp1);
        return timestampList;
    }
}
