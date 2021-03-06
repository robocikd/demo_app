package sda.projekt.rezerwacje.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.projekt.rezerwacje.infrastructure.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
