package ru.innopolis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.CityEntity;
import ru.innopolis.pojo.LocationEntity;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LocationDto {
    private long id;
    private String titlePlace;
    private long cityId;
    private String street;
    private String houseNumber;
    private Float longitude;
    private Float latitude;
    private CityEntity cityByCityId;

    public static LocationDto from(LocationEntity locationEntity) {
        return LocationDto.builder().id(locationEntity.getId())
                .titlePlace(locationEntity.getTitlePlace())
                .cityId(locationEntity.getCityId())
                .street(locationEntity.getStreet())
                .houseNumber(locationEntity.getHouseNumber())
                .longitude(locationEntity.getLongitude())
                .latitude(locationEntity.getLatitude())
                .cityByCityId(locationEntity.getCityByCityId())
                .build();
    }

    public static List<LocationDto> from(List<LocationEntity> locationEntity) {
        return locationEntity.stream().map(LocationDto::from).collect(Collectors.toList());
    }
}
