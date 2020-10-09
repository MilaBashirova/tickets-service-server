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
@Table(name = "posters", schema = "public", catalog = "postgres")
public class PostersEntity {
    private long id;
    private String poster;

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
    @Column(name = "poster", nullable = true, length = -1)
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostersEntity that = (PostersEntity) o;
        return id == that.id &&
                Objects.equals(poster, that.poster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, poster);
    }

}
