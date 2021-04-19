package mk.ukim.finki.chartair.service;

import mk.ukim.finki.chartair.model.Cart;
import mk.ukim.finki.chartair.model.Reservation;

import java.util.List;

public interface CartService {
    List<Reservation> listAllReservationsCart(Long cartId);
    Cart getActiveCart(String username);
    Cart addReservationToCart(String username, Long productId);

}

