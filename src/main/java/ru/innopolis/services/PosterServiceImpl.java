package ru.innopolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.dto.PosterDto;
import ru.innopolis.pojo.PostersEntity;
import ru.innopolis.repositories.PosterRepository;

/**
 * @author Bashirova Milyausha
 */
@Component
public class PosterServiceImpl implements PosterService {
    @Autowired
    PosterRepository posterRepository;
    @Override
    public PostersEntity addPoster(PosterDto posterDto) {
        PostersEntity postersEntity= PostersEntity.builder().poster(posterDto.getPoster()).build();
        PostersEntity poster=posterRepository.save(postersEntity);

        return poster;
    }
}
