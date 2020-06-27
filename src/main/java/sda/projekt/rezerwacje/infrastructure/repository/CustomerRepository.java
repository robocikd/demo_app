package sda.projekt.rezerwacje.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.projekt.rezerwacje.infrastructure.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByLogin(String login);
    Customer findCustomerByEmail(String email);
    Customer findCustomerByConfirmationToken(String token);
}
