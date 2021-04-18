package mk.ukim.finki.chartair.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Country {

    @Id
    @GeneratedValue
    private Long countryId;

    private String countryName;

    public Country() {
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }
}
