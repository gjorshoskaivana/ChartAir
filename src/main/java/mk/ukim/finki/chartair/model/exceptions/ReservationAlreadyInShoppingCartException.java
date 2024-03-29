package mk.ukim.finki.chartair.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ReservationAlreadyInShoppingCartException extends RuntimeException{
    public ReservationAlreadyInShoppingCartException(Long id, String username) {
        super(String.format("Reservation with id: %d already exists in shopping cart for user with username %s", id, username));
    }


}
