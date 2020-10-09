package ru.innopolis.repositories;/**
 * @author KhafizovR by 21.02.2020
 * tickets-service-server
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.pojo.RoleEntity;
import ru.innopolis.pojo.RoleEnum;
import ru.innopolis.pojo.TicketsEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(RoleEnum role);

    @Query(value = "select * from role where role='ROLE_USER'", nativeQuery = true)
    Optional<RoleEntity> getDefaultRole();
}
