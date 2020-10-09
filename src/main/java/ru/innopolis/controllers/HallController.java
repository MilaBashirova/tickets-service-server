package ru.innopolis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.dto.CityDto;
import ru.innopolis.dto.HallsDto;
import ru.innopolis.services.HallService;

import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
@RestController
public class HallController {
    @Autowired
    HallService hallService;

    @GetMapping("/halls")
    public Optional<HallsDto> getCityByName(@RequestParam(value = "hallId", required = false) Long id) {
        HallsDto hallsDto=hallService.getHallById(id).get();
        return hallService.getHallById(id);
    }
}
