package ru.innopolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.dto.HallsDto;
import ru.innopolis.pojo.HallsEntity;
import ru.innopolis.repositories.HallRepository;

import java.util.Optional;

/**
 * @author Bashirova Milyausha
 */
@Component
public class HallServiceImpl implements HallService {
    @Autowired
    HallRepository hallRepository;
    @Override
    public Optional<HallsDto> getHallById(Long id) {
        HallsEntity hallsEntity=hallRepository.getHallsEntityById(id);
        return Optional.of(HallsDto.from(hallsEntity));
    }
}
