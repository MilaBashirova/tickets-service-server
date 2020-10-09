package ru.innopolis.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "activity_location", schema = "public", catalog = "postgres")
public class ActivityLocationEntity {
    private long id;
    private long locationId;
    private long hallsId;
    private LocationEntity locationByLocationId;
    private HallsEntity hallsByHallsId;

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
    @Column(name = "location_id", nullable = false,updatable = false,insertable = false)
    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "halls_id", nullable = false,updatable = false,insertable = false)
    public long getHallsId() {
        return hallsId;
    }

    public void setHallsId(long hallsId) {
        this.hallsId = hallsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityLocationEntity that = (ActivityLocationEntity) o;
        return id == that.id &&
                locationId == that.locationId &&
                hallsId == that.hallsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locationId, hallsId);
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    public LocationEntity getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(LocationEntity locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }

    @ManyToOne
    @JoinColumn(name = "halls_id", referencedColumnName = "id", nullable = false)
    public HallsEntity getHallsByHallsId() {
        return hallsByHallsId;
    }

    public void setHallsByHallsId(HallsEntity hallsByHallsId) {
        this.hallsByHallsId = hallsByHallsId;
    }
}
