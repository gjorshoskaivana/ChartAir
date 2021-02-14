package mk.ukim.finki.chartair.repository;

import mk.ukim.finki.chartair.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
