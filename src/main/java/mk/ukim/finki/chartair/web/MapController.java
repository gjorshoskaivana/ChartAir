package mk.ukim.finki.chartair.web;

import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = {"/map"})
public class MapController {

    private final FlightService flightService;

    public MapController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public String getMapPage(Model model) {
        model.addAttribute("bodyContent", "map");
        return "master-template";
    }

    @GetMapping("/searchFlight")
    public String searchFlight(@RequestParam(required = false) String nameSearch, Model model){
        List<Flight> listFlights = this.flightService.findAllByDepartureCity(nameSearch);
        model.addAttribute("listFlights", listFlights);
        model.addAttribute("bodyContent", "map");
        return "master-template";
    }
}
