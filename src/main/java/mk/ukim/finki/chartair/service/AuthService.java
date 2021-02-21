package mk.ukim.finki.chartair.service;

import mk.ukim.finki.chartair.model.User;

public interface AuthService {
    User login(String username, String password);
}
