package ru.innopolis.services;

import ru.innopolis.pojo.UsersActivityEntity;

import java.util.List;

/**
 * @author Bashirova Milyausha
 */
public interface UsersActivityService {
    List<UsersActivityEntity> getAllByUserIdAndTicketsIdIn(Long userId, List<Long> ticketsId);

}
