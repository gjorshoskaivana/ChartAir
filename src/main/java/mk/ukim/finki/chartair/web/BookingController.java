package mk.ukim.finki.chartair.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/booking"})
public class BookingController {
    @GetMapping
    public String getBookingPage(Model model) {
        model.addAttribute("bodyContent", "booking");
        return "master-template";
    }
}
