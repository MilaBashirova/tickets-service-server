package ru.innopolis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.ActivityDto;
import ru.innopolis.pojo.ActivityEntity;
import ru.innopolis.services.ActivityService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static ru.innopolis.dto.ActivityDto.from;


/**
 * @author Bashirova Milyausha
 */
@RestController
public class ActivityController {
    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/addActivity")
    public ResponseEntity<ActivityDto> addActivity(@RequestBody ActivityDto activityDto) {
        ActivityEntity activity = activityService.addActivity(activityDto);
        ActivityDto resultAdd=from(activity);
        return ResponseEntity.of(Optional.of(resultAdd));
    }

    @PutMapping("/updateActivity")
    public ResponseEntity<ActivityDto> updateActivity(@RequestBody ActivityDto activityDto) {
        activityService.updateActivity(activityDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteActivity")
    public ResponseEntity<ActivityDto> deleteActivity(@RequestBody ActivityDto activityDto) {
        activityService.deleteActivity(activityDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activity")
    public List<ActivityDto> getActivity(@RequestParam(value = "title", required = false) String title,
                                         @RequestParam(value = "city", required = false) String city,
                                         @RequestParam(value = "type", required = false) String type,
                                         @RequestParam(value = "date", required = false) Date date,
                                         @RequestParam(value = "dateStart", required = false) Date dateStart,
                                         @RequestParam(value = "dateEnd", required = false) Date dateEnd) {
        List<ActivityDto> activityDtos=getActivityByParam(title, city, type, date, dateStart, dateEnd);
        return activityDtos;
    }

    private List<ActivityDto> getActivityByParam(String title, String city, String type,
                                                 Date date, Date dateStart, Date dateEnd) {
        String methodIndex = "";
        methodIndex += (title != null) ? "1" : "0";
        methodIndex += (city != null) ? "1" : "0";
        methodIndex += (type != null) ? "1" : "0";
        methodIndex += (date != null) ? "1" : "0";
        methodIndex += (dateStart != null && dateEnd != null) ? "1" : "0";

        switch (methodIndex) {
            case "10000":
                return activityService.findAllActivityByTitle(title);
            case "01000":
                return activityService.findAllActivityByCity(city);
            case "00100":
                return activityService.findAllActivityByType(type);
            case "00010":
                return activityService.findAllActivityByDate(date);
            case "00001":
                return activityService.findAllActivityByPeriod(dateStart, dateEnd);
            case "11000":
                return activityService.findAllActivityByTitleAndCity(title, city);
            case "01100":
                return activityService.findAllActivityByCityAndType(city, type);
            case "01010":
                return activityService.findAllActivityByDateAndCity(date, city);
            case "01001":
                return activityService.findAllActivityByPeriodAndCity(dateStart, dateEnd, city);
            default:
                return null;
        }

    }


}
