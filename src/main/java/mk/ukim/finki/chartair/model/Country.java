package mk.ukim.finki.chartair.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue
    private Long countryId;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    public Country() {
    }

    public Country(List<City> cities) {
        this.cities = cities;
    }
}
