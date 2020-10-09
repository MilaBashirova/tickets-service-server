package ru.innopolis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.CityEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bashirova Milyausha
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CityDto {
    private long id;
    private String city;
    private String timeZone;

    public static CityDto from(CityEntity cityEntity) {
        return CityDto.builder().id(cityEntity.getId())
                .city(cityEntity.getCity())
                .timeZone(cityEntity.getTimeZone())
                .build();
    }

    public static List<CityDto> from(List<CityEntity> cityEntities) {
        return cityEntities.stream().map(CityDto::from).collect(Collectors.toList());
    }
}
