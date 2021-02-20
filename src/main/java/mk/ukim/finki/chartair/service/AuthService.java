package mk.ukim.finki.chartair.service;

import org.apache.catalina.User;

public interface AuthService {
    User login(String username, String password);
}
