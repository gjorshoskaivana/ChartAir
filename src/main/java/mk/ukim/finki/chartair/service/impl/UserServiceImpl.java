package mk.ukim.finki.chartair.service.impl;

import mk.ukim.finki.chartair.model.enumerations.Role;
import mk.ukim.finki.chartair.model.User;
import mk.ukim.finki.chartair.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.chartair.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.chartair.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.chartair.repository.UserRepository;
import mk.ukim.finki.chartair.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        Role role = Role.ROLE_USER;
        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
