package ru.innopolis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.dto.ActivityDateHallsDto;
import ru.innopolis.dto.CityDto;
import ru.innopolis.pojo.ActivityDateHallsEntity;
import ru.innopolis.services.ActivityDateHallsEntityService;
import ru.innopolis.services.ActivityService;

import java.util.List;
import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
@RestController
public class ActivityDateController {
    private ActivityDateHallsEntityService  activityDateHallsEntityService;

    @Autowired
    public ActivityDateController(ActivityDateHallsEntityService activityDateHallsEntityService) {
        this.activityDateHallsEntityService = activityDateHallsEntityService;
    }
    @GetMapping("/activityDate")
    public List<ActivityDateHallsDto> getActivityDateByActivityId(@RequestParam(value="activityId", required = false) Long id) {
        return activityDateHallsEntityService.getActivityDateByActivityId(id);
    }
}

