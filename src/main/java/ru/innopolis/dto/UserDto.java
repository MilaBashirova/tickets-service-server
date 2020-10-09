package ru.innopolis.dto;
/**
 * @author Helen Podolskaya
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.*;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String password;
    private String email;
    private String phone;
    private CityDto city;

    public UserDto(long userId, String userFirstName, String userLastName, long userCityId, String userEmail, String userPassword, String userPhone) {
        id = userId;
        firstName = userFirstName;
        lastName = userLastName;
        email = userEmail;
        phone = userPhone;
        password = userPassword;
    }

    public static UserDto from(UsersEntity userEntity) {
        return UserDto.builder().id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .middleName(userEntity.getMiddleName())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .city(CityDto.from(userEntity.getCity()))
                .build();
    }
}
