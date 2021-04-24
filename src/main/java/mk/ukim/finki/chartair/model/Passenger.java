package mk.ukim.finki.chartair.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
