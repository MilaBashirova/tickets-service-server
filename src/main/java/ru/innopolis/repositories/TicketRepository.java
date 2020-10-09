package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.pojo.ActivityDateHallsEntity;
import ru.innopolis.pojo.TicketStatusEnum;
import ru.innopolis.pojo.TicketsEntity;
import ru.innopolis.pojo.UsersActivityEntity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.Optional;

/**
 * @author Helen Podolskaya
 * @author Alina Mangutova (4 for halls & change getTicketsEntitiesByUserID)
 */
@Repository
public interface TicketRepository extends JpaRepository<TicketsEntity, Long> {
    /* @Query(value = "select adh.activity_date, l.title_place,adto.title,h.number, t.row_num,t.place_num, t.ticket_status " +
             "from tickets t " +
             "join activity_date_halls adh on t.act_date_halls_id = adh.id " +
             "join activity adto on adh.activity_id = adto.id " +
             "join location l on adto.location_id = l.id " +
             "join halls h on adh.halls_id = h.id " +
             "where t.id = ?1", nativeQuery = true)*/
    //  @Query(value = "select * from tickets where id=?1", nativeQuery = true)
    //  TicketsEntity  getTicketsEntityById(Long ticketId);
    @Query(value = " select distinct tickets.* from tickets join users_activity ua on tickets.id = ua.tickets_id " +
            "         where user_id=?1 order by act_date_halls_id", nativeQuery = true)
    List<TicketsEntity> getTicketsEntitiesByUserID(Long userId);

    @Query(value = " select * from tickets join activity_date_halls adh on tickets.act_date_halls_id = adh.id " +
            " where adh.activity_id=?1", nativeQuery = true)
    List<TicketsEntity> getAllTicketsByActivityId(Long activityId);

    /*это выборка всех билетов в зале по id зала*/
    @Query(value = " select distinct tickets.* from tickets join activity_date_halls adh on tickets.act_date_halls_id = adh.id " +
            " where adh.halls_id=?1", nativeQuery = true)
    List<TicketsEntity> getAllTicketsByHallsId(Long hallsId);

    /*это выборка всех билетов по залу и дата_активити*/
    @Query(value = " select distinct tickets.* from tickets join activity_date_halls adh on tickets.act_date_halls_id = adh.id " +
            "                      join halls h on adh.halls_id = h.id " +
            "                      where h.id=?1 and adh.id=?2 order by tickets.id", nativeQuery = true)
    List<TicketsEntity> getActivityTicketsByHallsId(Long hallsId, Long actDHId);

    /*это выборка всех билетов для конкретного юзера в зале по id зала и id юзера */
    @Query(value = " select distinct tickets.* from tickets join users_activity ua on tickets.id = ua.tickets_id " +
            "                      join activity_date_halls adh on tickets.act_date_halls_id = adh.id " +
            "                      join halls h on adh.halls_id = h.id " +
            "             where h.id=?1 and user_id=?2  order by id", nativeQuery = true)
    List<TicketsEntity> getAllTicketsByHallsUserId(Long hallsId, Long userId);

    /* выборка билетов для конкретного юзера по залу и id времени мероприятия*/
    @Query(value = "select distinct tickets.* from tickets join users_activity ua on tickets.id = ua.tickets_id" +
            "                      join activity_date_halls adh on tickets.act_date_halls_id = adh.id" +
            "                      join halls h on adh.halls_id = h.id" +
            "                      where h.id=?1 and adh.id=?2 and user_id=?3 order by tickets.id", nativeQuery = true)
    List<TicketsEntity> getActivityTicketsByHallsUserId(Long hallsId, Long actDHId, Long userId);

    @Modifying
    @Transactional
    // @Query(value = "update tickets set ticket_status = ?1:: enum_ticket_status where id = ?2", nativeQuery = true)
    @Query(value = "update tickets set ticket_status = 'free' where id = ?1", nativeQuery = true)
    void updateTicketStatusById(/*String status, */ Long ticketId);

    @Modifying
    @Transactional
    // @Query(value = "update tickets set ticket_status = ?1:: enum_ticket_status where id = ?2", nativeQuery = true)
    @Query(value = "update tickets set ticket_status = 'blocked' where id = ?1", nativeQuery = true)
    void updateTicketStatusToBlockedById(/*String status, */ Long ticketId);

    @Modifying
    @Transactional
    // @Query(value = "update tickets set ticket_status = ?1:: enum_ticket_status where id = ?2", nativeQuery = true)
    @Query(value = "update tickets set ticket_status = 'sales' where id = ?1", nativeQuery = true)
    void updateTicketStatusToSaleById(/*String status, */Long ticketId);

    @Query(value = "select * from tickets " +
            "    join activity_date_halls adh on tickets.act_date_halls_id = adh.id " +
            "where (select localtimestamp)<= adh.activity_date and extract (day from( localtimestamp-adh.activity_date))=0 and " +
            " extract(epoch  from adh.activity_date)-extract(epoch from localtimestamp)<=1800", nativeQuery = true)
    List<TicketsEntity> getAllTicketsAfterNow();

    @Modifying
    @Transactional
    @Query(value = "update tickets set ticket_status='free' where " +
            " id in (select tickets.id from tickets join activity_date_halls adh on tickets.act_date_halls_id = adh.id " +
            "    where (select localtimestamp)<= adh.activity_date and extract (day from( localtimestamp-adh.activity_date))=0 and " +
            "            extract(epoch  from adh.activity_date)-extract(epoch from localtimestamp)<=1800 and tickets.ticket_status='blocked') ", nativeQuery = true)
    void setTicketsStatusFreeWhereStartTimeLess30();
    @Query(value = "select * from tickets u where u.act_date_halls_id=?1", nativeQuery = true)
    List<TicketsEntity> findAllByActDateHallsId(Long activityDateId);

    //@Query(value = "select * from tickets u where uid=?1", nativeQuery = true)
    List<TicketsEntity> findAllByIdIn(List<Long> listId);


}
