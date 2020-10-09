package ru.innopolis.services;

import ru.innopolis.dto.HallsDto;

import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
public interface HallService {
    Optional<HallsDto> getHallById(Long id);
}
