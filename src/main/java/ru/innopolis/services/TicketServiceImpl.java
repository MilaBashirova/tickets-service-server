package ru.innopolis.services;

import org.hibernate.event.internal.OnUpdateVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.pojo.TicketStatusEnum;
import ru.innopolis.repositories.UserActivityRepository;
import ru.innopolis.dto.TicketDto;
import ru.innopolis.dto.UserActivityDto;
import ru.innopolis.dto.UserDto;
import ru.innopolis.pojo.TicketsEntity;
import ru.innopolis.pojo.UsersActivityEntity;
import ru.innopolis.repositories.TicketRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.innopolis.dto.TicketDto.from;

@Service
@Transactional
@Component
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserActivityRepository userActivityRepository;
    @Autowired
    ActivityDateHallsEntityService activityDateHallsEntityService;
    @Autowired
    UsersActivityService usersActivityService;

    @Override
    public Optional<TicketDto> getTicketById(Long ticketId) {
        TicketsEntity ticketsEntity = ticketRepository.getOne(ticketId);//.getTicketsEntityById(ticketId);
        Optional<TicketDto> ticketDto = Optional.of(TicketDto.from(ticketsEntity));
        return ticketDto;
    }

    @Override
    public TicketsEntity updateTicketStatusByTicketId(TicketDto ticketDto) {
        TicketsEntity ticketsEntity = TicketsEntity.fromTicketDto(ticketDto);
        ticketRepository.updateTicketStatusById(/*ticketsEntity.getTicketStatus().toString(), */ticketsEntity.getId());
        return ticketRepository.getOne(ticketDto.getId());
    }

    @Override
    public List<TicketsEntity> getTicketsByUserId(Long userId) {
        return ticketRepository.getTicketsEntitiesByUserID(userId);
    }

    @Override
    public List<TicketsEntity> getAllTicketsByActivityId(Long activityID) {
        return ticketRepository.getAllTicketsByActivityId(activityID);
    }

    // выборка для зала
    @Override
    public List<TicketsEntity> getAllTicketsByHallsId(Long hallsId) {
        return ticketRepository.getAllTicketsByHallsId(hallsId);
    }

    // выборка билетов по залу и активити дата
    @Override
    public List<TicketsEntity> getActivityTicketsByHallsId(Long hallsId, Long actDHId) {
        return ticketRepository.getActivityTicketsByHallsId(hallsId, actDHId);
    }

    // выборка для юзера билетов по залу
    @Override
    public List<TicketsEntity> getAllTicketsByHallsUserId(Long hallsId, Long userId) {
        return ticketRepository.getAllTicketsByHallsUserId(hallsId, userId);
    }

    // выборка для юзера билетов по залу и активити дата
    @Override
    public List<TicketsEntity> getActivityTicketsByHallsUserId(Long hallsId, Long actDHId, Long userId) {
        return ticketRepository.getActivityTicketsByHallsUserId(hallsId, actDHId, userId);
    }


    @Override
    public List<TicketsEntity> getAllTicketsAfterNow() {
        return ticketRepository.getAllTicketsAfterNow();
    }

    @Override
    public void updateTicketsStatusFreeWhereStartTimeLess30() {
        ticketRepository.setTicketsStatusFreeWhereStartTimeLess30();
    }

    /*бронирование билетов
     * установка билету статуса blocked
     * вставка в таблицу user_activity */
    @Override
    public List<TicketsEntity> addTicketToUser(List<TicketDto> ticketDtoList, Long userId) {
        for (TicketDto userActivityDto : ticketDtoList) {
            userActivityRepository.insertIntoUserActivity(userId, userActivityDto.getActivityDateHallsDto().getActivityId(), userActivityDto.getId());
            ticketRepository.updateTicketStatusToBlockedById(/*status,*/userActivityDto.getId());
        }
        return ticketRepository.getTicketsEntitiesByUserID(userId);
    }

    @Override
    public List<TicketsEntity> delTicketToUser(List<Long> ticketIds, Long userId) {
        for (Long ticketId : ticketIds) {
            ticketRepository.updateTicketStatusById(ticketId);
            userActivityRepository.delete(ticketId);
        }
        return ticketRepository.getTicketsEntitiesByUserID(userId);
    }

    @Override
    public List<TicketsEntity> setTicketsStatusSale(List<Long> ticketIds, Long userId) {
        for (Long ticketId : ticketIds) {
            ticketRepository.updateTicketStatusToSaleById(ticketId);
        }
        return ticketRepository.getTicketsEntitiesByUserID(userId);
    }

    @Override
    public List<UsersActivityEntity> getAllUserActivities() {
        return userActivityRepository.findAll();
    }


    @Override
    public List<TicketDto> getTicketsByActivityIdAndHallsIdAndActivityDate(Long activityId, Long hallsId, Timestamp activityDate) {
        Optional<Long> activityDateId = activityDateHallsEntityService.getActivityDateByActivityIdAndHallsIdAndActivityDate(activityId, hallsId, activityDate);
        if (activityDateId.isPresent()) {
            List<TicketsEntity> ticketsEntities = ticketRepository.findAllByActDateHallsId(activityDateId.get());
            return from(ticketsEntities);

        } else return null;
    }

    @Override
    public List<TicketDto> getTicketsByActivityIdAndHallsIdAndActivityDateAndUserId(Long activityId, Long hallsId, Timestamp activityDate, Long userId) {
        List<TicketDto> ticketDtoList = getTicketsByActivityIdAndHallsIdAndActivityDate(activityId, hallsId, activityDate);
        List<Long> listTicketsId = new ArrayList<>();
        for (TicketDto ticket : ticketDtoList) {
            listTicketsId.add(ticket.getId());
        }
        List<UsersActivityEntity> usersActivityEntities =usersActivityService.getAllByUserIdAndTicketsIdIn(userId,listTicketsId);
        listTicketsId.clear();
        for (UsersActivityEntity userActivity : usersActivityEntities) {
            listTicketsId.add(userActivity.getTicketsId());
        }
        List<TicketsEntity> ticketsEntities=ticketRepository.findAllByIdIn(listTicketsId);
        return from(ticketsEntities);
    }
}
