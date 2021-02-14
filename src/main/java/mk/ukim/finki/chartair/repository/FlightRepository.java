package mk.ukim.finki.chartair.repository;

import mk.ukim.finki.chartair.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
