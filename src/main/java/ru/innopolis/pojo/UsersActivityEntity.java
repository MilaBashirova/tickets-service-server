package ru.innopolis.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.dto.ActivityDto;
import ru.innopolis.dto.UserActivityDto;
import ru.innopolis.dto.UserDto;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users_activity", schema = "public", catalog = "postgres")
public class UsersActivityEntity {
    private long id;
    private long userId;
    private Long activityId;
    private Long ticketsId;
    private UsersEntity usersByUserId;
    private ActivityEntity activityByActivityId;
    private TicketsEntity ticketsByTicketsId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, updatable = false, insertable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "activity_id", nullable = true, updatable = false, insertable = false)
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "tickets_id", nullable = true, updatable = false, insertable = false)
    public Long getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(Long ticketsId) {
        this.ticketsId = ticketsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersActivityEntity that = (UsersActivityEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(activityId, that.activityId) &&
                Objects.equals(ticketsId, that.ticketsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, activityId, ticketsId);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    public ActivityEntity getActivityByActivityId() {
        return activityByActivityId;
    }

    public void setActivityByActivityId(ActivityEntity activityByActivityId) {
        this.activityByActivityId = activityByActivityId;
    }

    @ManyToOne
    @JoinColumn(name = "tickets_id", referencedColumnName = "id")
    public TicketsEntity getTicketsByTicketsId() {
        return ticketsByTicketsId;
    }

    public void setTicketsByTicketsId(TicketsEntity ticketsByTicketsId) {
        this.ticketsByTicketsId = ticketsByTicketsId;
    }

    public static UsersActivityEntity fromUserActivityDto(UserActivityDto userActivityDto) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setId(userActivityDto.getActivityByActivityId().getId());
        activityEntity.setTitle(userActivityDto.getActivityByActivityId().getTitle());
        activityEntity.setActivityTypeByTypeId(userActivityDto.getActivityByActivityId().getActivityTypeByTypeId());
        activityEntity.setLocationByLocationId(userActivityDto.getActivityByActivityId().getLocationByLocationId());
        activityEntity.setPostersByPosterId(userActivityDto.getActivityByActivityId().getPostersByPosterId());
        TicketsEntity ticketsEntity = new TicketsEntity();
        ticketsEntity.setId(userActivityDto.getTicketsByTicketsId().getId());
        ticketsEntity.setActDateHallsId(userActivityDto.getTicketsByTicketsId().getActDateHallsId());
        ticketsEntity.setPlaceNum(userActivityDto.getTicketsByTicketsId().getPlaceNum());
        ticketsEntity.setRowNum(userActivityDto.getTicketsByTicketsId().getRowNum());
        ticketsEntity.setSignNum(userActivityDto.getTicketsByTicketsId().getSignNum());
        ticketsEntity.setTicketStatus(userActivityDto.getTicketsByTicketsId().getTicketStatus());
        ticketsEntity.setActivityDateHallsByActDateHallsId(ActivityDateHallsEntity.fromActivityDateHallsDto(userActivityDto.getTicketsByTicketsId().getActivityDateHallsDto()));
        return UsersActivityEntity.builder().id(userActivityDto.getId())
                .userId(userActivityDto.getUserId())
                .activityId(userActivityDto.getActivityId())
                .ticketsId(userActivityDto.getTicketsId())
                .activityByActivityId(activityEntity)
                .ticketsByTicketsId(ticketsEntity)
                .build();
    }
}
