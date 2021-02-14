package mk.ukim.finki.chartair.repository;

import mk.ukim.finki.chartair.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
