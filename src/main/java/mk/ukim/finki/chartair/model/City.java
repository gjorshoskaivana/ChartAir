package mk.ukim.finki.chartair.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class City {

    @Id
    @GeneratedValue
    private Long cityId;

    private String cityName;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "departureCity")
    private List<Flight> departingFlights;

    @OneToMany(mappedBy = "arrivalCity")
    private List<Flight> arrivingFlights;

    public City() {
    }

    public City(String cityName, Country country, List<Flight> departingFlights, List<Flight> arrivingFlights) {
        this.cityName = cityName;
        this.country = country;
        this.departingFlights = departingFlights;
        this.arrivingFlights = arrivingFlights;
    }
}
