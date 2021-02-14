package mk.ukim.finki.chartair.model;

import javax.persistence.*;
import java.util.List;

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

    public City(String cityName, Country country, List<Flight> departingFlights) {
        this.cityName = cityName;
        this.country = country;
        this.departingFlights = departingFlights;
    }
}
