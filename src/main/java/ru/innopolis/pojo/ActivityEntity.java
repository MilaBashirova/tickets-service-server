package ru.innopolis.pojo;

import lombok.*;
import ru.innopolis.dto.ActivityDto;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "activity", schema = "public", catalog = "postgres")
public class ActivityEntity {
    private long id;
    private long typeId;
    private String title;
    private Long locationId;
    private Long posterId;
    private ActivityTypeEntity activityTypeByTypeId;
    private LocationEntity locationByLocationId;
    private PostersEntity postersByPosterId;


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
    @Column(name = "type_id", nullable = false,updatable = false,insertable = false)
    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "location_id", nullable = true,updatable = false,insertable = false)
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "poster_id", nullable = true,updatable = false,insertable = false)
    public Long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return id == that.id &&
                typeId == that.typeId &&
                Objects.equals(title, that.title) &&
                Objects.equals(locationId, that.locationId) &&
                Objects.equals(posterId, that.posterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeId, title, locationId, posterId);
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    public ActivityTypeEntity getActivityTypeByTypeId() {
        return activityTypeByTypeId;
    }

    public void setActivityTypeByTypeId(ActivityTypeEntity activityTypeByTypeId) {
        this.activityTypeByTypeId = activityTypeByTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    public LocationEntity getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(LocationEntity locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }

    @ManyToOne
    @JoinColumn(name = "poster_id", referencedColumnName = "id")
    public PostersEntity getPostersByPosterId() {
        return postersByPosterId;
    }

    public void setPostersByPosterId(PostersEntity postersByPosterId) {
        this.postersByPosterId = postersByPosterId;
    }

    public static ActivityEntity fromActivityDto(ActivityDto activityDto) {
        return   ActivityEntity.builder().title(activityDto.getTitle())
                .typeId(activityDto.getActivityTypeByTypeId().getId())
                .locationId(activityDto.getLocationByLocationId().getId())
                .posterId(activityDto.getPostersByPosterId().getId())
                .locationByLocationId(activityDto.getLocationByLocationId())
                .activityTypeByTypeId(activityDto.getActivityTypeByTypeId())
                .postersByPosterId(activityDto.getPostersByPosterId())
                .build();
    }
}
