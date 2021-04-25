package mk.ukim.finki.chartair.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private LocalDateTime departure;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
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
