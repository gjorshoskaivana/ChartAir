package mk.ukim.finki.chartair.service;

import mk.ukim.finki.chartair.model.City;
import mk.ukim.finki.chartair.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FlightService {
    List<Flight> listAll();
    List<Flight> findAllByDepartureCity(Long departureId);
    List<Flight> findAllByDepartureAndArrivalCity(Long departureId, Long arrivalId);
    Flight create(LocalDateTime departure, LocalDateTime arrival, City departureCity, City arrivalCity);
    Optional<Flight> findById(Long id);
}
