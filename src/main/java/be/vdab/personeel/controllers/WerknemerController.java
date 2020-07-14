package be.vdab.personeel.controllers;

import be.vdab.personeel.WerknemerNietGevondenException;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/werknemers")
@PreAuthorize("hasAuthority('werknemer')")
public class WerknemerController {

    private final WerknemerService service;

    public WerknemerController(WerknemerService service) {
        this.service = service;
    }

    @GetMapping
    public String werknemers() {
        var id = service.findByChefIsNull().get().getId();
        return "redirect:/werknemers/" + id;
    }

    @GetMapping("{id}")
    public ModelAndView werknemer(@PathVariable long id) {
        var modelAndView = new ModelAndView("werknemer");
        service.findById(id).ifPresent(werknemer -> {
            modelAndView.addObject("werknemer", werknemer);
            modelAndView.addObject("ondergeschikten", service.findByChef(werknemer));
        });
        return modelAndView;
    }

    @GetMapping("{id}/opslag")
    public ModelAndView opslag(@PathVariable long id) {
        var modelAndView = new ModelAndView("opslag");
        service.findById(id).ifPresent(
                werknemer -> modelAndView.addObject("werknemer", werknemer));
        return modelAndView;
    }

    @PostMapping("{id}/opslag")
    public String geefOpslag(@PathVariable long id, BigDecimal bedrag, RedirectAttributes redirect) {
        try {
            service.opslag(id, bedrag);
            redirect.addAttribute("opslagGelukt", true);
        } catch (IllegalArgumentException | NullPointerException ex) {
            redirect.addAttribute("bedragFout", true);
        } catch (WerknemerNietGevondenException ex) {
            redirect.addAttribute("werknemerFout", true);
        }
        return "redirect:/werknemers/{id}/opslag";
    }
}
