package mk.ukim.finki.chartair.service;

import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
