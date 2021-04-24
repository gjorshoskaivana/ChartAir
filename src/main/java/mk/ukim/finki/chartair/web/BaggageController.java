package mk.ukim.finki.chartair.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/baggage"})
public class BaggageController {
    @GetMapping
    public String getMapPage(Model model) {
        model.addAttribute("bodyContent", "baggage");
        return "master-template";
    }
}
