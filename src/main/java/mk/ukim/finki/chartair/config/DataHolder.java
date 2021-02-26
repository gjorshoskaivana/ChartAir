package mk.ukim.finki.chartair.config;

import mk.ukim.finki.chartair.model.Role;
import mk.ukim.finki.chartair.model.User;
import mk.ukim.finki.chartair.service.CityService;
import mk.ukim.finki.chartair.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataHolder {

    private final UserService userService;
    private final CityService cityService;

    public DataHolder(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;
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
    }
}
