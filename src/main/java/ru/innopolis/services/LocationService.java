package ru.innopolis.services;

import ru.innopolis.dto.LocationDto;

import java.util.List;

/**
 * @author Bashirova Milyausha
 */
public interface LocationService {
    List<LocationDto> getLocationsByCityId(long cityId);
    List<Long> getLocationsIdByCityId(long cityId);

}
