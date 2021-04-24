package mk.ukim.finki.chartair.config;

import mk.ukim.finki.chartair.model.enumerations.Role;
import mk.ukim.finki.chartair.model.User;
import mk.ukim.finki.chartair.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

import static mk.ukim.finki.chartair.model.enumerations.TravelClass.ECONOMY;

@Component
public class DataHolder {

    private final UserService userService;
    private final CityService cityService;
    private final FlightService flightService;
    private final ReservationService reservationService;
    //private final PassengerService passengerService;


    public DataHolder(UserService userService, CityService cityService, FlightService flightService, ReservationService reservationService) {
        this.userService = userService;
        this.cityService = cityService;
        this.flightService = flightService;
        this.reservationService = reservationService;
    }
    /*
    @PostConstruct
    public void initData(){
        User user = userService.register("user", "user", "user",
                "user", "user");
        User admin = userService.register("admin", "admin", "admin",
                "admin", "admin");

        for(int i=0; i<10; i++){
            this.cityService.create("City"+i, null);
        }

        flightService.create(LocalDateTime.now(), LocalDateTime.now(),
                this.cityService.findCitiesByNameLike("City0").get(0),
                this.cityService.findCitiesByNameLike("City1").get(0));

    }*/
}
