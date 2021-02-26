package mk.ukim.finki.chartair.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Flight {

    @Id
    @GeneratedValue
    private Long flightId;

    private LocalDateTime departure;

    private LocalDateTime expectedLanding;

    @ManyToOne
    private City departureCity;

    @ManyToOne
    private City arrivalCity;

    public Flight() {
    }

    public Flight(LocalDateTime departure, LocalDateTime expectedLanding, City departureCity, City arrivalCity) {
        this.departure = departure;
        this.expectedLanding = expectedLanding;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getExpectedLanding() {
        return expectedLanding;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }
}
