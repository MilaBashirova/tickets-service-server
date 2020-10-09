package ru.innopolis.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "activity_type", schema = "public", catalog = "postgres")
public class ActivityTypeEntity {
    private long id;
    private String activityType;

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
    @Column(name = "activity_type", nullable = false, length = 50)
    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityTypeEntity that = (ActivityTypeEntity) o;
        return id == that.id &&
                Objects.equals(activityType, that.activityType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activityType);
    }

}
