package ru.innopolis.dto;
/**
 * @author Podolskaya Helen
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TicketDto {
    private long id;
    private long actDateHallsId;
    private Integer rowNum;
    private Integer placeNum;
    private Double signNum;
    private TicketStatusEnum ticketStatus;
    private ActivityDateHallsDto activityDateHallsDto;
    private Collection<UsersActivityEntity> usersActivitiesById;


    public static TicketDto from(TicketsEntity ticketsEntity) {
        return TicketDto.builder().id(ticketsEntity.getId())
                .actDateHallsId(ticketsEntity.getActDateHallsId())
                .rowNum(ticketsEntity.getRowNum())
                .placeNum(ticketsEntity.getPlaceNum())
                .signNum(ticketsEntity.getSignNum())
                .ticketStatus(ticketsEntity.getTicketStatus())
                .activityDateHallsDto(ActivityDateHallsDto.from(ticketsEntity.getActivityDateHallsByActDateHallsId()))
                .build();
    }

    public static List<TicketDto> from(List<TicketsEntity> ticketsEntities) {
        return ticketsEntities.stream().map(TicketDto::from).collect(Collectors.toList());
    }

}
