package mk.ukim.finki.chartair.repository;

import mk.ukim.finki.chartair.model.City;
import mk.ukim.finki.chartair.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAllByDepartureCity(City departureCity);
}
