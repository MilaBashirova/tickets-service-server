package ru.innopolis.pojo;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "activity_date_halls", schema = "public", catalog = "postgres")
public class ActivityDateHallsEntity {
    private long id;
    private Timestamp activityDate;
    private Long hallsId;
    private long activityId;
    private HallsEntity hallsByHallsId;
    private ActivityEntity activityByActivityId;

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
    @Column(name = "activity_date", nullable = true)
    public Timestamp getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Timestamp activityDate) {
        this.activityDate = activityDate;
    }

    @Basic
    @Column(name = "halls_id", nullable = true, updatable = false, insertable = false)
    public Long getHallsId() {
        return hallsId;
    }

    public void setHallsId(Long hallsId) {
        this.hallsId = hallsId;
    }

    @Basic
    @Column(name = "activity_id", nullable = false, updatable = false, insertable = false)
    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityDateHallsEntity that = (ActivityDateHallsEntity) o;
        return id == that.id &&
                activityId == that.activityId &&
                Objects.equals(activityDate, that.activityDate) &&
                Objects.equals(hallsId, that.hallsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activityDate, hallsId, activityId);
    }

    @ManyToOne
    @JoinColumn(name = "halls_id", referencedColumnName = "id")
    public HallsEntity getHallsByHallsId() {
        return hallsByHallsId;
    }

    public void setHallsByHallsId(HallsEntity hallsByHallsId) {
        this.hallsByHallsId = hallsByHallsId;
    }

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    public ActivityEntity getActivityByActivityId() {
        return activityByActivityId;
    }

    public void setActivityByActivityId(ActivityEntity activityByActivityId) {
        this.activityByActivityId = activityByActivityId;
    }

    public static ActivityDateHallsEntity fromActivityDateHallsDto(ru.innopolis.dto.ActivityDateHallsDto activityDateHallsDto) {
        HallsEntity hallsEntity = new HallsEntity(activityDateHallsDto.getHallsByHallsId().getId(),
                activityDateHallsDto.getHallsByHallsId().getNumber(), activityDateHallsDto.getHallsByHallsId().getTicketsCount(),
                activityDateHallsDto.getHallsByHallsId().getScheme());
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setId(activityDateHallsDto.getActivityByActivityId().getId());
        activityEntity.setTypeId(activityDateHallsDto.getActivityByActivityId().getActivityTypeByTypeId().getId());
        activityEntity.setTitle(activityDateHallsDto.getActivityByActivityId().getTitle());
        activityEntity.setLocationId(activityDateHallsDto.getActivityByActivityId().getLocationByLocationId().getId());
        activityEntity.setPosterId(activityDateHallsDto.getActivityByActivityId().getPostersByPosterId().getId());
        return ActivityDateHallsEntity.builder().id(activityDateHallsDto.getId())
                .activityDate(activityDateHallsDto.getActivityDate())
                .hallsId(activityDateHallsDto.getHallsId())
                .activityId(activityDateHallsDto.getActivityId())
                .hallsByHallsId(hallsEntity)
                .activityByActivityId(activityEntity)
                .build();
    }
}
