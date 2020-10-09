package ru.innopolis.services;

import ru.innopolis.dto.CityDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
public interface CityService {
    List<CityDto> getAllCity();
    CityDto getCityById(Long cityId);
    Optional<CityDto> getCityByName(String name);
}
