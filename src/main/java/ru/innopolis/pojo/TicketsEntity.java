package ru.innopolis.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.dto.TicketDto;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tickets", schema = "public", catalog = "postgres")
public class TicketsEntity {
    private long id;
    private long actDateHallsId;
    private Integer rowNum;
    private Integer placeNum;
    private Double signNum;
    private TicketStatusEnum ticketStatus;
    private ActivityDateHallsEntity activityDateHallsByActDateHallsId;

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
    @Column(name = "act_date_halls_id", nullable = false)
    public long getActDateHallsId() {
        return actDateHallsId;
    }

    public void setActDateHallsId(long actDateHallsId) {
        this.actDateHallsId = actDateHallsId;
    }

    @Basic
    @Column(name = "row_num", nullable = true)
    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    @Basic
    @Column(name = "place_num", nullable = true)
    public Integer getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(Integer placeNum) {
        this.placeNum = placeNum;
    }

    @Basic
    @Column(name = "sign_num", nullable = true, length = 10)
    public Double getSignNum() {
        return signNum;
    }

    public void setSignNum(Double signNum) {
        this.signNum = signNum;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status", nullable = true)
    public TicketStatusEnum getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatusEnum ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketsEntity that = (TicketsEntity) o;
        return id == that.id &&
                actDateHallsId == that.actDateHallsId &&
                Objects.equals(rowNum, that.rowNum) &&
                Objects.equals(placeNum, that.placeNum) &&
                Objects.equals(signNum, that.signNum) &&
                Objects.equals(ticketStatus, that.ticketStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actDateHallsId, rowNum, placeNum, signNum, ticketStatus);
    }

    @ManyToOne
    @JoinColumn(name = "act_date_halls_id", referencedColumnName = "id", nullable = false,updatable = false,insertable = false)
    public ActivityDateHallsEntity getActivityDateHallsByActDateHallsId() {
        return activityDateHallsByActDateHallsId;
    }

    public void setActivityDateHallsByActDateHallsId(ActivityDateHallsEntity activityDateHallsByActDateHallsId) {
        this.activityDateHallsByActDateHallsId = activityDateHallsByActDateHallsId;
    }


    public static TicketsEntity fromTicketDto(TicketDto ticketDto) {
       ActivityDateHallsEntity activityDateHallsEntity =new ActivityDateHallsEntity();
       activityDateHallsEntity.setId(ticketDto.getActivityDateHallsDto().getId());
       activityDateHallsEntity.setActivityDate(ticketDto.getActivityDateHallsDto().getActivityDate());
       activityDateHallsEntity.setHallsId(ticketDto.getActivityDateHallsDto().getHallsId());
       activityDateHallsEntity.setActivityId(ticketDto.getActivityDateHallsDto().getActivityId());
        return   TicketsEntity.builder().id(ticketDto.getId())
                .actDateHallsId(ticketDto.getActDateHallsId())
                .rowNum(ticketDto.getRowNum())
                .placeNum(ticketDto.getPlaceNum())
                .signNum(ticketDto.getSignNum())
                .ticketStatus(ticketDto.getTicketStatus())
                .activityDateHallsByActDateHallsId(activityDateHallsEntity)
               .build();
    }
}
