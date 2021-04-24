package mk.ukim.finki.chartair.web;

import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    public final FlightService flightService;

    public CalendarController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getCalendar(@RequestParam(required = false) Long departureId, Model model){
        List<Flight> flightList;
        if (departureId == null){
            flightList = this.flightService.findAllByDepartureCity(1L);
        } else{
            flightList = this.flightService.findAllByDepartureCity(departureId);
        }
        return flightList;
    }
}
