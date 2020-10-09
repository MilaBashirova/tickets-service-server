package ru.innopolis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.pojo.PostersEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bashirova Milyausha
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PosterDto {
    private long id;
    private String poster;

    public static PosterDto from(PostersEntity posterEntity) {
        return PosterDto.builder().id(posterEntity.getId()).poster(posterEntity.getPoster())
                .build();
    }

    public static List<PosterDto> from(List<PostersEntity> postersEntities) {
        return postersEntities.stream().map(PosterDto::from).collect(Collectors.toList());
    }
}
