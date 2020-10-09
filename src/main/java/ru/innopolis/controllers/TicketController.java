package ru.innopolis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.CityDto;
import ru.innopolis.dto.TicketDto;

import ru.innopolis.dto.UserActivityDto;
import ru.innopolis.dto.UserDto;
import ru.innopolis.pojo.TicketsEntity;

import ru.innopolis.pojo.UsersActivityEntity;
import ru.innopolis.repositories.TicketRepository;
import ru.innopolis.services.ActivityDateHallsEntityService;
import ru.innopolis.services.CityService;
import ru.innopolis.services.TicketService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static ru.innopolis.dto.UserDto.from;

/**
 * @author Helen Podolskaya
 * @author Alina Mangutova (only 4 for halls)
 */
@RestController
public class TicketController {
    TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

/*
    @GetMapping("/ticket")
    public List<TicketDto> getTicketsByActivityIdAndHallsIdAndActivityDate(@RequestParam(value = "activityId", required = false) Long activityId,
                                                                           @RequestParam(value = "hallsId", required = false) Long hallsId,
                                                                           @RequestParam(value = "activityDate", required = false) String activityDate,
                                                                           @RequestParam(value = "userId", required = false) Long userId) {
        if (userId != null && hallsId != null && activityId != null && activityDate != null) {
            Timestamp timestamp1 = Timestamp.valueOf(activityDate);
            return ticketService.getTicketsByActivityIdAndHallsIdAndActivityDateAndUserId(activityId, hallsId, timestamp1,userId);
        }
        if (userId == null && hallsId != null && activityId != null && activityDate != null) {
            Timestamp timestamp1 = Timestamp.valueOf(activityDate);
            return ticketService.getTicketsByActivityIdAndHallsIdAndActivityDate(activityId, hallsId, timestamp1);
        }
        else return null;
    }*/

    @GetMapping("/ticket/{id}")
    public Optional<TicketDto> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping("/updateTicketStatus")
    public ResponseEntity<TicketDto> updateTicketStatusByTicketId(@RequestBody TicketDto ticketDto) {
        TicketsEntity ticket = ticketService.updateTicketStatusByTicketId(ticketDto);
        TicketDto result = TicketDto.from(ticket);
        return ResponseEntity.of(Optional.of(result));
    }

    @GetMapping("/ticketsByUserId")
    public List<TicketDto> getTicketsByUserId(@RequestParam Long userId) {
        List<TicketsEntity> ticketsEntityList = ticketService.getTicketsByUserId(userId);
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    @PostMapping("/allTicketsByActivityId/{activityId}")
    public List<TicketDto> getAllTicketsByActivityId(@PathVariable Long activityId) {
        List<TicketsEntity> ticketsEntityList = ticketService.getAllTicketsByActivityId(activityId);
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    //это выборка всех билетов в зале по id зала
    @GetMapping("/allTicketsByHallsId")
    public List<TicketDto> getAllTicketsByHallsId(@RequestParam Long hallsId) {
        List<TicketsEntity> ticketsEntityList = ticketService.getAllTicketsByHallsId(hallsId);
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    //это выборка всех билетов в зале по id зала и id активити дата
    @GetMapping("/activityTicketsByHallsId")
    public List<TicketDto> getActivityTicketsByHallsId(@RequestParam(value = "hallsId", required = false) Long hallsId, @RequestParam(value = "actDHId", required = false) Long actDHId) {
        List<TicketsEntity> ticketsEntityList = ticketService.getActivityTicketsByHallsId(hallsId, actDHId);
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    //это выборка всех билетов для конкретного юзера в зале по id зала и id юзера
    @GetMapping("/allTicketsByHallsUserId")
    public List<TicketDto> getAllTicketsByHallsUserId(@RequestParam(value = "hallsId", required = false) Long hallsId, @RequestParam(value = "userId", required = false) Long userId) {
        List<TicketsEntity> ticketsEntityList = ticketService.getAllTicketsByHallsUserId(hallsId, userId);
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    //это выборка всех билетов для конкретного юзера в зале по id зала и id юзера и id активити дата
    @GetMapping("/activityTicketsByHallsUserId")
    public List<TicketDto> getActivityTicketsByHallsUserId(@RequestParam(value = "hallsId", required = false) Long hallsId, @RequestParam(value = "actDHId", required = false) Long actDHId, @RequestParam(value = "userId", required = false) Long userId) {
        List<TicketsEntity> ticketsEntityList = ticketService.getActivityTicketsByHallsUserId(hallsId, actDHId, userId);
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    /*добавление списка строк в таблицу UserActivity(по одному пользователю)
    с обновлением статуса билетов
    */
    @PostMapping("/addTicketsList/{userId}")
    public List<TicketDto> setTicketsList(@RequestBody List<TicketDto> userActivityDtos, @PathVariable Long userId) {
        List<TicketsEntity> ticketsEntityList = ticketService.addTicketToUser(userActivityDtos, userId);
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    /*обновление статуса билета на free, если пользователь решил снять бронь неоплаченного билета
     * удаление из таблицы user_activity*/
    @PostMapping("/delTicketsList/{userId}")
    public List<TicketDto> delTicketsList(@RequestBody List<Long> ticketIds, @PathVariable Long userId) {
        List<TicketsEntity> ticketsEntityList = ticketService.delTicketToUser(ticketIds, userId);
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    /*обновление статуса билета на sales, если пользователь решил оплатить билеты */
    @PostMapping("/setTicketsStatusSale/{userId}")
    public List<TicketDto> setTicketsStatusSale(@RequestBody List<Long> ticketIds, @PathVariable Long userId) {
        List<TicketsEntity> ticketsEntityList = ticketService.setTicketsStatusSale(ticketIds, userId);
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    @GetMapping("/getUserActivityList")
    public List<UserActivityDto> getUserActivityList() {
        List<UsersActivityEntity> userActivityDtoList = ticketService.getAllUserActivities();
        List<UserActivityDto> result = UserActivityDto.from(userActivityDtoList);
        return result;
    }

    @GetMapping("/getAllTicketsListAfterNow")
    public List<TicketDto> getAllTicketsListAfterNow() {
        List<TicketsEntity> ticketsEntityList = ticketService.getAllTicketsAfterNow();
        List<TicketDto> result = TicketDto.from(ticketsEntityList);
        return result;
    }

    @PostMapping("/setTicketsStatusFreeWhereStartTimeLess30")
    public int setTicketsStatusFreeWhereStartTimeLess30() {
        ticketService.updateTicketsStatusFreeWhereStartTimeLess30();
        return 1;
    }
}
