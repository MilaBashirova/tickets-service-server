package ru.innopolis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.pojo.PostersEntity;

/**
 * @author Bashirova Milyausha
 */
public interface PosterRepository extends JpaRepository<PostersEntity,Long> {
}
