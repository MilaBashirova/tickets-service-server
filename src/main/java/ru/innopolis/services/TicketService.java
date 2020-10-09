package ru.innopolis.services;

import ru.innopolis.dto.CityDto;
import ru.innopolis.dto.TicketDto;
import ru.innopolis.dto.UserActivityDto;
import ru.innopolis.dto.UserDto;
import ru.innopolis.pojo.TicketsEntity;
import ru.innopolis.pojo.UsersActivityEntity;
import ru.innopolis.pojo.UsersEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author Helen Podolskaya
 * @author Alina Mangutova (only 4 for halls)
 */
public interface TicketService {
    Optional<TicketDto> getTicketById(Long ticketId);

    List<TicketsEntity> getAllTicketsByActivityId(Long activityID);

    TicketsEntity updateTicketStatusByTicketId(TicketDto ticketDto);

    List<TicketsEntity> getAllTicketsAfterNow();

    void updateTicketsStatusFreeWhereStartTimeLess30();

    List<TicketsEntity> setTicketsStatusSale(List<Long> ticketIds, Long userId);

    List<TicketsEntity> getTicketsByUserId(Long userId);

    List<TicketsEntity> addTicketToUser(List<TicketDto> ticketDtoList, Long userId);

    List<TicketsEntity> delTicketToUser(List<Long> ticketIds, Long userId);

    List<UsersActivityEntity> getAllUserActivities();

    // выборка для зала
    List<TicketsEntity> getAllTicketsByHallsId(Long hallsId);

    // выборка билетов по залу и активити
    List<TicketsEntity> getActivityTicketsByHallsId(Long hallsId, Long actDHId);

    // выборка для юзера билетов по залу
    List<TicketsEntity> getAllTicketsByHallsUserId(Long hallsId, Long userId);

    // выборка для юзера билетов по залу и активити
    List<TicketsEntity> getActivityTicketsByHallsUserId(Long hallsId, Long actDHId, Long userId);

    List<TicketDto> getTicketsByActivityIdAndHallsIdAndActivityDate(Long activityId, Long hallsId, Timestamp activityDate);

    List<TicketDto> getTicketsByActivityIdAndHallsIdAndActivityDateAndUserId(Long activityId, Long hallsId, Timestamp activityDate, Long userId);
}
