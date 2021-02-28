package mk.ukim.finki.chartair.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Passenger {

    @Id
    @GeneratedValue
    private Long passengerId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    @ManyToOne
    private Reservation reservation;

    public Passenger() {
    }

    public Passenger(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
