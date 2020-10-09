package ru.innopolis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.RoleEntity;
import ru.innopolis.pojo.UserRoleEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRoleDto {
    private long id;
    private long userId;
    private long roleId;

    public static UserRoleDto from(UserRoleEntity userRoleEntity) {
        return UserRoleDto.builder().id(userRoleEntity.getId())
                .userId(userRoleEntity.getUserId())
                .roleId(userRoleEntity.getRoleId())
                .build();
    }

    public static Collection<UserRoleDto> from(Collection<UserRoleEntity> userRoleEntity) {
        return userRoleEntity.stream().map(UserRoleDto::from).collect(Collectors.toList());
    }
}
