package be.vdab.personeel.controllers;

import be.vdab.personeel.services.JobtitelService;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("jobtitels")
public class JobtitelsController {

    private final JobtitelService jobtitelService;
    private final WerknemerService werknemerService;

    public JobtitelsController(JobtitelService jobtitelService, WerknemerService werknemerService) {
        this.jobtitelService = jobtitelService;
        this.werknemerService = werknemerService;
    }

    @GetMapping
    public ModelAndView jobtitels() {
        return new ModelAndView("jobtitels", "jobtitels",
                jobtitelService.findAll());
    }

    @GetMapping("{id}")
    public ModelAndView werknemersMetEenJobtitel(@PathVariable long id) {
        var modelAndView = new ModelAndView("jobtitels", "jobtitels", jobtitelService.findAll());
        jobtitelService.findById(id).ifPresent(jobtitel -> {
            modelAndView.addObject("jobtitel", jobtitel);
            modelAndView.addObject("werknemers", werknemerService.findByJobtitel(jobtitel));
        });
        return modelAndView;
    }
}
