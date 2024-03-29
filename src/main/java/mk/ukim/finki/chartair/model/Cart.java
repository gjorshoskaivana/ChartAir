package mk.ukim.finki.chartair.model;

import lombok.Data;
import mk.ukim.finki.chartair.model.enumerations.ShoppingCartStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToMany
    private List<Reservation> reservations;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;


    public Cart(){}

    public Cart(User user){
        this.user = user;
        this.reservations = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
