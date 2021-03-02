package mk.ukim.finki.chartair.web;

import mk.ukim.finki.chartair.model.Cart;
import mk.ukim.finki.chartair.model.User;
import mk.ukim.finki.chartair.service.CartService;
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

    public ShoppingCartController(CartService cartService) {
        this.cartService = cartService;
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
    @PostMapping("/add-reservation/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            this.cartService.addProductToCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }


}
