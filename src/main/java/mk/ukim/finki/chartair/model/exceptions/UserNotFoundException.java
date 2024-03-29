package mk.ukim.finki.chartair.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username){
        super(String.format("user with username: %s was not found", username));
    }
}
