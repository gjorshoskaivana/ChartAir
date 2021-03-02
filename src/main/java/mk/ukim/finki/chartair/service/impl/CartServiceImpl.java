package mk.ukim.finki.chartair.service.impl;

import mk.ukim.finki.chartair.model.Cart;
import mk.ukim.finki.chartair.model.Reservation;
import mk.ukim.finki.chartair.model.ShoppingCartStatus;
import mk.ukim.finki.chartair.model.User;
import mk.ukim.finki.chartair.model.exceptions.ReservationAlreadyInShoppingCartException;
import mk.ukim.finki.chartair.model.exceptions.ReservationNotFoundException;
import mk.ukim.finki.chartair.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.chartair.model.exceptions.UserNotFoundException;
import mk.ukim.finki.chartair.repository.CartRepository;
import mk.ukim.finki.chartair.repository.UserRepository;
import mk.ukim.finki.chartair.service.CartService;
import mk.ukim.finki.chartair.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ReservationService reservationService;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ReservationService reservationService) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.reservationService = reservationService;
    }


    @Override
    public List<Reservation> listAllReservationsCart(Long cartId) {
        if(!this.cartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.cartRepository.findById(cartId).get().getReservations();

    }

    @Override
    public Cart getActiveCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.cartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    Cart cart = new Cart(user);
                    return this.cartRepository.save(cart);
                });

    }

    @Override
    public Cart addProductToCart(String username, Long reservationId) {
        Cart shoppingCart = this.getActiveCart(username);
        Reservation reservation = this.reservationService.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
        if(shoppingCart.getReservations()
                .stream().filter(i -> i.getReservationId().equals(reservationId))
                .collect(Collectors.toList()).size() > 0)
            throw new ReservationAlreadyInShoppingCartException(reservationId, username);
        shoppingCart.getReservations().add(reservation);
        return this.cartRepository.save(shoppingCart);
    }

}
