package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.pojo.TicketsEntity;
import ru.innopolis.pojo.UsersActivityEntity;

import java.util.List;

/**
 * @author Helen Podolskaya
 */
@Repository
public interface UserActivityRepository extends JpaRepository<UsersActivityEntity, Long> {
    List<UsersActivityEntity> getAllByUserId(Long userId);
    @Modifying
    @Transactional
    @Query(value = "insert into users_activity (id,user_id,activity_id,tickets_id) values " +
            " (default,?1,?2,?3)", nativeQuery = true)
    void insertIntoUserActivity(Long userId, Long activityId, Long ticketId);
    @Modifying
    @Transactional
    @Query(value = "delete from users_activity where tickets_id = ?1", nativeQuery = true)
    int delete(Long id);

//    @Query(value = "select * from users_activity u where u.userId=?1 and u.tickets_id=?2", nativeQuery = true)
    List<UsersActivityEntity> findAllByUserIdAndTicketsIdIn(Long userId,List<Long> ticketsId);

}
