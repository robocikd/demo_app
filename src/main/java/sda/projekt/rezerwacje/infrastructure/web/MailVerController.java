package sda.projekt.rezerwacje.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sda.projekt.rezerwacje.domain.CustomerService;
import sda.projekt.rezerwacje.infrastructure.entity.Customer;

@RequiredArgsConstructor
@Controller
public class MailVerController {

    private final CustomerService customerService;

    @GetMapping("/confirm/{token}")
    String enableCustomer(@PathVariable String token) {
        Customer customer = customerService.findCustomerByConfirmationToken(token);
        if (customer == null) {
            return "errorPage.html";
        }
        customer.setEnable(true);
        customer.setConfirmationToken(null);
        customerService.patch(customer);
        return "loginPage.html";
    }

}
