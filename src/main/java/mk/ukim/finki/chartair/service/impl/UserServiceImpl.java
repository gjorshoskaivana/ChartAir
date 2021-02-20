package mk.ukim.finki.chartair.service.impl;

import mk.ukim.finki.chartair.service.UserService;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
