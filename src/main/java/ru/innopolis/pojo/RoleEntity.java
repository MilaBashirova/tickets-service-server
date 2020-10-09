package ru.innopolis.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.innopolis.dto.RoleDto;
import ru.innopolis.dto.UserRoleDto;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "role", schema = "public", catalog = "postgres")
public class RoleEntity {

    private long id;
    private RoleEnum role;
    private Collection<UserRoleEntity> userRolesById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }


    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<UserRoleEntity> getUserRolesById() {
        return userRolesById;
    }

    public void setUserRolesById(Collection<UserRoleEntity> userRolesById) {
        this.userRolesById = userRolesById;
    }

    public static RoleEntity fromRoleDto(RoleDto roleDto) {
        return RoleEntity.builder().id((int) roleDto.getId())
                .role(roleDto.getRole())
                .build();
    }

    public static Collection<RoleEntity> from(Collection<RoleDto> roleDto) {
        return roleDto.stream().map(RoleEntity::fromRoleDto).collect(Collectors.toList());
    }
}
