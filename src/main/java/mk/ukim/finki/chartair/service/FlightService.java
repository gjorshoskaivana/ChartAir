package mk.ukim.finki.chartair.service;

import mk.ukim.finki.chartair.model.City;
import mk.ukim.finki.chartair.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    List<Flight> listAll();
    List<Flight> findAllByDepartureAndArrivalCity(Long departureId, Long arrivalId);
    Flight create(LocalDateTime departure, LocalDateTime arrival, City departureCity, City arrivalCity);
}
