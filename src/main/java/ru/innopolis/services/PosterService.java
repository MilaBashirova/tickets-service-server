package ru.innopolis.services;

import ru.innopolis.dto.PosterDto;
import ru.innopolis.pojo.PostersEntity;

/**
 * @author Bashirova Milyausha
 */
public interface PosterService {
    PostersEntity addPoster(PosterDto posterDto);

}
