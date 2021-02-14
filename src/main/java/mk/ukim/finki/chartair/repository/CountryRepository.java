package mk.ukim.finki.chartair.repository;

import mk.ukim.finki.chartair.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
