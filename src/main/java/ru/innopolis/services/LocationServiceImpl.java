package ru.innopolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.dto.LocationDto;
import ru.innopolis.pojo.LocationEntity;
import ru.innopolis.repositories.LocationRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.dto.LocationDto.from;

/**
 * @author Bashirova Milyausha
 */
@Component
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<LocationDto> getLocationsByCityId(long cityId) {
        List<LocationEntity> locationEntities = locationRepository.findAllByCityId(cityId);
        List<LocationDto> locationEntityDtos = from(locationEntities);
        return locationEntityDtos;
    }

    @Override
    public List<Long> getLocationsIdByCityId(long cityId) {
        List<LocationEntity> locationEntities=locationRepository.findAllByCityId(cityId);
        List<Long> listLocationId=new ArrayList<>();
        locationEntities.stream().map(LocationEntity::getId).forEach(listLocationId::add);

        return listLocationId;
    }
}
