package mk.ukim.finki.chartair.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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
}
