package ru.innopolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.pojo.UsersActivityEntity;
import ru.innopolis.repositories.UserActivityRepository;

import java.util.List;

/**
 * @author Bashirova Milyausha
 */
@Component
public class UsersActivityServiceImpl implements UsersActivityService {
    @Autowired
    UserActivityRepository userActivityRepository;
    @Override
    public List<UsersActivityEntity> getAllByUserIdAndTicketsIdIn(Long userId, List<Long> ticketsId) {
        List<UsersActivityEntity> activityEntityList=userActivityRepository.findAllByUserIdAndTicketsIdIn(userId,ticketsId);
        return activityEntityList;
    }
}
