package mk.ukim.finki.chartair.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue
    private Long countryId;

    private String countryName;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    public Country() {
    }

    public Country(String countryName, List<City> cities) {
        this.countryName = countryName;
        this.cities = cities;
    }
}
