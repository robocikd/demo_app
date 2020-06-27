package sda.projekt.rezerwacje.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sda.projekt.rezerwacje.infrastructure.dto.CustomerDto;
import sda.projekt.rezerwacje.infrastructure.entity.Customer;
import sda.projekt.rezerwacje.infrastructure.mapper.CustomerMapper;
import sda.projekt.rezerwacje.infrastructure.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;

    private final CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDto dto) {
        return customerRepository.save(customerMapper.toEntity(dto));
    }

    public Customer findCustomerByLogin(String login) {
        return customerRepository.findCustomerByLogin(login);
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    public String getLoggedUserLogin() {
        String login = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            login = authentication.getName();
        }
        return login;
    }

    public Customer findCustomerByConfirmationToken(String token) {
        return customerRepository.findCustomerByConfirmationToken(token);
    }

    public void patch(Customer customer) {
        customerRepository.save(customer);
    }
}
