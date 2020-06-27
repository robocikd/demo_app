package sda.projekt.rezerwacje.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sda.projekt.rezerwacje.domain.CottageService;
import sda.projekt.rezerwacje.domain.EmailService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@RequiredArgsConstructor
@Controller
public class MainViewController {

    private final CottageService cottageService;
    @GetMapping("/")
    ModelAndView showIndexView() {
        ModelAndView modelAndView = new ModelAndView("index.html");
        modelAndView.addObject("cottages", cottageService.findAll());

        return modelAndView;
    }
}
