package ru.innopolis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.dto.CityDto;
import ru.innopolis.pojo.CityEntity;
import ru.innopolis.services.CityService;

import java.util.List;
import java.util.Optional;

/**
 * @author Bashirova Milyausha
        * Helen Podolskaya
        */
@RestController
public class CityController {
    CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping("/city")
    public List<CityDto> getAllCity() {
        return cityService.getAllCity();
    }
    @GetMapping("/cityname")
    public Optional<CityDto> getCityByName(@RequestParam(value="name", required = false) String name) {
        return cityService.getCityByName(name);
    }
    @GetMapping("/cityid")
    public CityDto getCityById(@RequestParam(value="id", required = false) Long cityId) {
        return cityService.getCityById(cityId);
    }
}
