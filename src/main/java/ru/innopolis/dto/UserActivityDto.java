package ru.innopolis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Helen Podolskaya
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserActivityDto {
    private long id;
    private long userId;
    private Long activityId;
    private Long ticketsId;
    private UsersEntity usersByUserId;
    private ActivityDto activityByActivityId;
    private TicketDto ticketsByTicketsId;


     public static UserActivityDto from(UsersActivityEntity userActivityEntity) {
        return UserActivityDto.builder().id(userActivityEntity.getId())
                .userId(userActivityEntity.getUserId())
                .activityId(userActivityEntity.getActivityId())
                .ticketsId(userActivityEntity.getTicketsId())
                .activityByActivityId(ActivityDto.from(userActivityEntity.getActivityByActivityId()))
                .ticketsByTicketsId(TicketDto.from(userActivityEntity.getTicketsByTicketsId()))
                .build();
    }

    public static List<UserActivityDto> from(List<UsersActivityEntity> usersActivityEntities) {
        return usersActivityEntities.stream().map(UserActivityDto::from).collect(Collectors.toList());
    }
}
