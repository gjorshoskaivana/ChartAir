package mk.ukim.finki.chartair.config;

import mk.ukim.finki.chartair.model.Role;
import mk.ukim.finki.chartair.model.User;
import mk.ukim.finki.chartair.service.CityService;
import mk.ukim.finki.chartair.service.FlightService;
import mk.ukim.finki.chartair.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class DataHolder {

    private final UserService userService;
    private final CityService cityService;
    private final FlightService flightService;

    public DataHolder(UserService userService, CityService cityService, FlightService flightService) {
        this.userService = userService;
        this.cityService = cityService;
        this.flightService = flightService;
    }

    @PostConstruct
    public void initData(){
        User user = userService.register("user", "user", "user",
                "user", "user", Role.ROLE_USER);
        User admin = userService.register("admin", "admin", "admin",
                "admin", "admin", Role.ROLE_ADMIN);

        for(int i=0; i<10; i++){
            this.cityService.create("City"+i, null, null, null);
        }

        flightService.create(LocalDateTime.now(), LocalDateTime.now(),
                this.cityService.findCitiesByNameLike("City0").get(0),
                this.cityService.findCitiesByNameLike("City1").get(0));
    }
}
