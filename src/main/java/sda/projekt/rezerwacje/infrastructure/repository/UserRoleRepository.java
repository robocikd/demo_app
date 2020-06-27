package sda.projekt.rezerwacje.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.projekt.rezerwacje.infrastructure.entity.UserRole;

import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Set<UserRole> getByRoleEquals(String role);
}
