package sda.projekt.rezerwacje.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sda.projekt.rezerwacje.domain.CustomerService;
import sda.projekt.rezerwacje.domain.EmailService;
import sda.projekt.rezerwacje.domain.ReservationService;
import sda.projekt.rezerwacje.infrastructure.dto.CustomerDto;
import sda.projekt.rezerwacje.infrastructure.entity.Customer;
import sda.projekt.rezerwacje.infrastructure.mapper.CustomerMapper;
import sda.projekt.rezerwacje.infrastructure.mapper.ReservationMapper;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;
    private final EmailService emailService;

    @GetMapping("/create")
    ModelAndView crateCustomerForm() {
        ModelAndView mav = new ModelAndView("createCustomer.html");
        mav.addObject("customerDto", new CustomerDto());
        return mav;
    }

    @PostMapping("/create")
    String createCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto, Errors errors, ModelAndView modelAndView) {
        Customer emailInUse = customerService.findCustomerByEmail(customerDto.getEmail());
        if (emailInUse != null) {
            errors.rejectValue("email", null, "mail zajety przez inna osobe");
            return "createCustomer.html";
        }
        Customer loginInUse = customerService.findCustomerByLogin(customerDto.getLogin());
        if (loginInUse != null) {
            errors.rejectValue("login", null, "login zajety przez inna osobe");
            return "createCustomer.html";
        }
        if (errors.hasErrors()) {
            return "createCustomer.html";
        }
        Customer customer = customerService.createCustomer(customerDto);
        emailService.sendSimpleMail(customerDto.getEmail(), "damian-rezerwacje verification",
                "https://damian-reservations.herokuapp.com/confirm/"+customer.getConfirmationToken());
        return "confirmMail.html";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/profile")
    ModelAndView getProfile() {
        ModelAndView modelAndView = new ModelAndView("customerProfile.html");
        String login = customerService.getLoggedUserLogin();
        Customer customer = customerService.findCustomerByLogin(login);
        modelAndView.addObject("customer", customerMapper.toDto(customer));
        modelAndView.addObject("reservations", reservationService.findAllByCustomer(customer));
        return modelAndView;
    }

}
