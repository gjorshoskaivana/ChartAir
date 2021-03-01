package mk.ukim.finki.chartair.web;

import mk.ukim.finki.chartair.model.City;
import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.service.CityService;
import mk.ukim.finki.chartair.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("bodyContent", "booking");
        return "master-template";
    }

    @GetMapping("/flights")
    public String getFlights(@RequestParam Long departure,
                             @RequestParam Long destination,
                             Model model){
        List<Flight> flightList = this.flightService.findAllByDepartureAndArrivalCity(departure, destination);
        model.addAttribute("flights", flightList);
        model.addAttribute("bodyContent", "list-flights");
        return "master-template";
    }
    @GetMapping("/select-package")
    public String selectFlight(@RequestParam Long departure,
                               @RequestParam Long destination,
                               Model model){
        //if (this.flightService.findById(id).isPresent()) {
            //Flight departureflight = this.flightService.findById(departure).get();
            //Flight destination = this.flightService.findById(departure).get();
            model.addAttribute("departure", this.cityService.findCityById(departure));
            model.addAttribute("destination", this.cityService.findCityById(destination));
            //model.addAttribute("flight", "departureflight");
           // model.addAttribute("msg",departureflight+"  ----->  "+destination);
            model.addAttribute("bodyContent", "select-flight");
            return "master-template";
        }

}
