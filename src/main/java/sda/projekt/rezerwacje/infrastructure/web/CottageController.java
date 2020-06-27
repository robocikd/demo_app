package sda.projekt.rezerwacje.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sda.projekt.rezerwacje.domain.CloudinaryService;
import sda.projekt.rezerwacje.domain.CottageFilesService;
import sda.projekt.rezerwacje.domain.CottageService;
import sda.projekt.rezerwacje.infrastructure.dto.CottageDto;
import sda.projekt.rezerwacje.infrastructure.entity.Cottage;

@Controller
@RequiredArgsConstructor
public class CottageController {

    public final CottageService cottageService;
    public final CottageFilesService cottageFilesService;
    public final CloudinaryService cloudinaryService;



    @GetMapping("/cottage/{region}")
    ModelAndView getCottagesByRegion(@PathVariable String region) {
        ModelAndView modelAndView = new ModelAndView("index.html");
        modelAndView.addObject("region", region);
        modelAndView.addObject("cottages", cottageService.findAllByRegion(region));
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cottage/create")
    ModelAndView createCottageView() {
        ModelAndView modelAndView = new ModelAndView("createCottage.html");
        modelAndView.addObject("cottage", new CottageDto());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cottage/create")
    String createCottage(@ModelAttribute CottageDto cottage, RedirectAttributes redirectAttributes, Model model) {

        Cottage dbCottage = cottageService.createOrUpdate(cottage);
        //String url = cloudinaryService.uploadFile(cottage.getFiles().get(0));
        if (dbCottage != null) {
            redirectAttributes.addFlashAttribute("successmessage", "Sukces!");
            return "redirect:/cottage/create";
        } else {
            model.addAttribute("errormessage", "Niestety! Nie dodano domku. Spróbuj ponownie.");
            model.addAttribute("cottage", cottage);
            return "createCottage.html";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cottage/delete")
    String deleteCottage(@RequestParam Long id, RedirectAttributes redirectAttributes, Model model) {
        if(id!=991 && id!=992 && id!=993 && id!=994){
            cottageService.delete(id);
            return "redirect:/";
        }else{
            model.addAttribute("errormessage", "Nie można usunąć tego domku!");
            return "index.html";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cottage/edit")
    ModelAndView editCottage(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("createCottage.html");
        modelAndView.addObject("cottage", cottageService.findById(id));
        return modelAndView;
    }

}
