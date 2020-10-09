package ru.innopolis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.CityEntity;
import ru.innopolis.pojo.RoleEntity;
import ru.innopolis.pojo.RoleEnum;

import javax.management.relation.Role;
import java.util.List;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoleDto {
    private long id;
    private RoleEnum role;
    public static RoleDto from(RoleEntity roleEntity) {
        return RoleDto.builder().id(roleEntity.getId())
                .role(roleEntity.getRole())
                .build();
    }

    public static List<RoleDto> from(List<RoleEntity> roleEntity) {
        return roleEntity.stream().map(RoleDto::from).collect(Collectors.toList());
    }
}
