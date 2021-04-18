package mk.ukim.finki.chartair.repository;

import mk.ukim.finki.chartair.model.Cart;
import mk.ukim.finki.chartair.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.chartair.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
