package mk.ukim.finki.chartair.config;

import mk.ukim.finki.chartair.model.Role;
import mk.ukim.finki.chartair.model.User;
import mk.ukim.finki.chartair.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataHolder {

    private final UserService userService;

    public DataHolder(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initData(){
        User user = userService.register("user", "user", "user",
                "user", "user", Role.ROLE_USER);
        User admin = userService.register("admin", "admin", "admin",
                "admin", "admin", Role.ROLE_ADMIN);
    }
}
