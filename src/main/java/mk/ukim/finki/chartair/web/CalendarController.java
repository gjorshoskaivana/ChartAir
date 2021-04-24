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
@RequestMapping("/calendar")
public class CalendarController {

    public final FlightService flightService;

    public CalendarController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public String getCalendar(@RequestParam(required = false) Long departureId, Model model){
        List<Flight> flightList;
        if (departureId == null){
            flightList = null;
        } else{
            flightList = this.flightService.findAllByDepartureCity(departureId);
        }
        model.addAttribute("flights", flightList);
        model.addAttribute("bodyContent", "calendar");
        return "master-template";
    }
}
