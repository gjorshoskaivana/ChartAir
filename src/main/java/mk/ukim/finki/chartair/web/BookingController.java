package mk.ukim.finki.chartair.web;

import mk.ukim.finki.chartair.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/booking"})
public class BookingController {

    private final CityService cityService;

    public BookingController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public String getBookingPage(Model model) {
        model.addAttribute("cities", this.cityService.findAll());
        model.addAttribute("bodyContent", "booking");
        return "master-template";
    }

}
