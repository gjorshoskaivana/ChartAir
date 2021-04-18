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


    public City() {
    }

    public City(String cityName, Country country) {
        this.cityName = cityName;
        this.country = country;
    }
}
