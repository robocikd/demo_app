package sda.projekt.rezerwacje.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sda.projekt.rezerwacje.infrastructure.dto.CustomerDto;
import sda.projekt.rezerwacje.infrastructure.entity.Customer;
import sda.projekt.rezerwacje.infrastructure.repository.UserRoleRepository;

import java.util.HashSet;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleRepository userRoleRepository;

    public Customer toEntity(CustomerDto dto) {

        Customer customer = new Customer();
        customer.setLogin(dto.getLogin());
        customer.setEmail(dto.getEmail());
        customer.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        customer.setRoles(userRoleRepository.getByRoleEquals("ROLE_USER"));
        customer.setCustomerName(dto.getCustomerName());
        customer.setCustomerSurname(dto.getCustomerSurname());
        customer.setCustomerAddress(dto.getCustomerAddress());
        customer.setReservations(new HashSet<>());
//        customer.setEnable(false);
        customer.setConfirmationToken(UUID.randomUUID().toString());
        return customer;
    }

    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .login(customer.getLogin())
                .email(customer.getEmail())
                .customerName(customer.getCustomerName())
                .customerSurname(customer.getCustomerSurname())
                .customerAddress(customer.getCustomerAddress())
                .build();
    }
}
