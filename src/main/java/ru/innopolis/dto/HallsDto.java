package ru.innopolis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.ActivityEntity;
import ru.innopolis.pojo.HallsEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helen Podolskaya
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HallsDto {
    private long id;
    private int number;
    private int ticketsCount;
    private String scheme;

    public static HallsDto from(HallsEntity hallsEntity) {
        return HallsDto.builder().id(hallsEntity.getId())
                .number(hallsEntity.getNumber())
                .ticketsCount(hallsEntity.getTicketsCount())
                .scheme(hallsEntity.getScheme())
                .build();
    }

    public static List<HallsDto> from(List<HallsEntity> hallsEntities) {
        return hallsEntities.stream().map(HallsDto::from).collect(Collectors.toList());
    }
}
