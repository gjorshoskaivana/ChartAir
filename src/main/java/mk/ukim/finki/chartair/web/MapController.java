package mk.ukim.finki.chartair.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/map"})
public class MapController {
    @GetMapping
    public String getMapPage(Model model) {
        model.addAttribute("bodyContent", "map");
        return "master-template";
    }
}
