package mk.ukim.finki.chartair.web;

import mk.ukim.finki.chartair.model.Cart;
import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.model.Reservation;
import mk.ukim.finki.chartair.model.User;
import mk.ukim.finki.chartair.model.enumerations.TravelClass;
import mk.ukim.finki.chartair.service.CartService;
import mk.ukim.finki.chartair.service.FlightService;
import mk.ukim.finki.chartair.service.ReservationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final CartService cartService;
    private final ReservationService reservationService;

    public ShoppingCartController(CartService cartService, ReservationService reservationService) {
        this.cartService = cartService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        Cart shoppingCart = this.cartService.getActiveCart(username);
        model.addAttribute("reservations", this.cartService.listAllReservationsCart(shoppingCart.getCartId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping
    public String addProductToShoppingCart(@RequestParam TravelClass travelClass,
                                           @RequestParam Integer noBags,
                                           HttpServletRequest req,
                                           Authentication authentication) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Integer numPassengers = (Integer)req.getSession().getAttribute("passengers");
            Integer numBags = noBags * numPassengers;
            Flight flight = (Flight)req.getSession().getAttribute("flight");

            Reservation r = this.reservationService.create(numBags, travelClass, numPassengers, flight);
            this.cartService.addReservationToCart(user.getUsername(), r.getReservationId());

            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Reservation reservation = this.reservationService.findById(id).orElseGet(null);
            this.cartService.deleteReservatioFromCart(user.getUsername(), reservation.getReservationId());
            this.reservationService.delete(id);
            return "redirect:/shopping-cart";

        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }


}
