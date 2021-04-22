package mk.ukim.finki.chartair.web;

import mk.ukim.finki.chartair.model.City;
import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.chartair.service.CityService;
import mk.ukim.finki.chartair.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = {"/booking"})
public class BookingController {

    private final CityService cityService;
    private final FlightService flightService;

    public BookingController(CityService cityService, FlightService flightService) {
        this.cityService = cityService;
        this.flightService = flightService;
    }

    @GetMapping
    public String getBookingPage(Model model) {
        model.addAttribute("cities", this.cityService.findAll());
        model.addAttribute("cities_ar", this.cityService.findAll());
        model.addAttribute("bodyContent", "booking");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getArrivingFlights(Long id, Model model){
        model.addAttribute("cities", this.cityService.findAll());
        model.addAttribute("cities_ar", this.cityService.findAllByDepartureCity(id));
        model.addAttribute("bodyContent", "booking");
        return "master-template";
    }


    @GetMapping("/flights")
    public String getFlights(@RequestParam Long departure,
                             @RequestParam Long destination,
                             @RequestParam int passengers,
                             Model model, HttpSession session){
        try{
            List<Flight> flightList = this.flightService.findAllByDepartureAndArrivalCity(departure, destination);
            model.addAttribute("flights", flightList);
            model.addAttribute("bodyContent", "list-flights");
            session.setAttribute("passengers", passengers);
            session.setAttribute("departure", this.cityService.findCityById(departure));
            session.setAttribute("destination", this.cityService.findCityById(destination));
            return "master-template";
        } catch (InvalidArgumentsException e){
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "redirect:/booking";
        }

    }

    @GetMapping("/select-package/{id}")
    public String selectFlight(@PathVariable Long id,
                               HttpSession session,
                               Model model){
        if(this.flightService.findById(id).isPresent()){
            Flight flight = this.flightService.findById(id).get();
            session.setAttribute("flight", flight);
            model.addAttribute("bodyContent", "select-flight");
            return "master-template";
        }
        return "redirect:/booking/flights";
    }
}
