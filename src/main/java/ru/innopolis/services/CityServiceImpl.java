package ru.innopolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.dto.CityDto;
import ru.innopolis.pojo.CityEntity;
import ru.innopolis.repositories.CityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.innopolis.dto.CityDto.from;

/**
 * @author Bashirova Milyausha
 * Helen Podolskaya
 */
@Service
@Transactional
@Component
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Override
    public List<CityDto> getAllCity() {
        List<CityEntity> cityEntity = cityRepository.getAll();
        List<CityDto> cityDto = from(cityEntity);
        return cityDto;
    }

    @Override
    public CityDto getCityById(Long cityId) {
        CityEntity cityEntity = cityRepository.getCityEntityById(cityId);
        CityDto cityDto = from(cityEntity);
        return cityDto;
    }

    @Override
    public Optional<CityDto> getCityByName(String name) {
        CityEntity cityEntity = cityRepository.getCityEntityByCity(name);
        Optional<CityDto> cityDto = Optional.of(from(cityEntity));
        return cityDto;
    }
}
