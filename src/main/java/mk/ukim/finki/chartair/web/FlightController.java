package mk.ukim.finki.chartair.web;

import mk.ukim.finki.chartair.model.City;
import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.service.CityService;
import mk.ukim.finki.chartair.service.FlightService;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    private final CityService cityService;

    public FlightController(FlightService flightService, CityService cityService) {
        this.flightService = flightService;
        this.cityService = cityService;
    }
    @GetMapping
    public String getFlightPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Flight> flights = this.flightService.listAll();
        model.addAttribute("flights", flights);
        model.addAttribute("bodyContent", "flights");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Long id){
        this.flightService.deleteById(id);
        return "redirect:/flights";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editFlightPage(@PathVariable Long id, Model model){
        if(this.flightService.findById(id).isPresent()){
            Flight flight = this.flightService.findById(id).get();
            List<City> cities = this.cityService.findAll();
            model.addAttribute("flight",flight);
            model.addAttribute("bodyContent", "add-flight");
            model.addAttribute("cities", cities);
            return "master-template";
        }
        return "redirect:/flights";
    }


    @PostMapping("/add")
    public String saveFlight(@RequestParam(required = false) Long id,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime departure,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime arrival,
                             @RequestParam Long departureCity, @RequestParam Long arrivalCity){
        City departure_city = this.cityService.findCityById(departureCity);
        City arrival_city = this.cityService.findCityById(arrivalCity);
        //LocalDateTime departure1 = LocalDateTime.parse(departure);
        //LocalDateTime expectedLanding1 = LocalDateTime.parse(expectedLanding);
        if(id != null){
            this.flightService.edit(id, departure, arrival, departure_city, arrival_city);
        }
        else{
            this.flightService.create(departure, arrival, departure_city, arrival_city);
        }
        return "redirect:/flights";
    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addFlightPage(Model model) {
        List<City> cities = this.cityService.findAll();
        model.addAttribute("bodyContent", "add-flight");
        model.addAttribute("cities", cities);
        return "master-template";
    }
}
