package ru.innopolis.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.dto.ActivityDto;
import ru.innopolis.dto.HallsDto;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "halls", schema = "public", catalog = "postgres")
public class HallsEntity {
    private long id;
    private int number;
    private int ticketsCount;
    private String scheme;


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
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "tickets_count", nullable = false)
    public int getTicketsCount() {
        return ticketsCount;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    @Basic
    @Column(name = "scheme", nullable = true, length = -1)
    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HallsEntity that = (HallsEntity) o;
        return id == that.id &&
                number == that.number &&
                ticketsCount == that.ticketsCount &&
                Objects.equals(scheme, that.scheme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, ticketsCount, scheme);
    }

    public static HallsEntity fromHallsDto(HallsDto hallsDto) {
        return HallsEntity.builder().id(hallsDto.getId())
                .number(hallsDto.getNumber())
                .ticketsCount(hallsDto.getTicketsCount())
                .scheme(hallsDto.getScheme())
                .build();
    }
}
