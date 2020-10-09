package ru.innopolis.services;

import ru.innopolis.pojo.ActivityLocationEntity;

import java.util.List;

/**
 * @author Bashirova Milyausha
 */
public interface ActivityLocationService {
    List<Long> getAllActivityLocationIdByLocationId(List<Long> locationsId);

}
