package sda.projekt.rezerwacje.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sda.projekt.rezerwacje.domain.CottageService;
import sda.projekt.rezerwacje.domain.ReservationService;
import sda.projekt.rezerwacje.infrastructure.dto.CottageDto;
import sda.projekt.rezerwacje.infrastructure.dto.ReservationDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cottage")
public class ReservationController {

    public final ReservationService reservationService;
    public final CottageService cottageService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/bookCottage/{index}")
    ModelAndView bookCottageView(@PathVariable Long index, ModelAndView modelAndView) {
        modelAndView.setViewName("bookCottage.html");
        modelAndView.addObject("cottage", cottageService.findById(index));
        modelAndView.addObject("reservation", new ReservationDto());
        return modelAndView;
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/bookCottage/{index}")
    String bookCottage(@PathVariable Long index, @ModelAttribute("reservation") ReservationDto reservationDto) {

        CottageDto byId = cottageService.findById(index);

        reservationService.canBeBooked(reservationDto, byId);
        reservationService.createReservation(reservationDto, byId);
        return "redirect:/customer/profile";
    }
}
