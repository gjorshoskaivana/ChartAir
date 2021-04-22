package mk.ukim.finki.chartair.service.impl;

import mk.ukim.finki.chartair.model.City;
import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.chartair.repository.FlightRepository;
import mk.ukim.finki.chartair.service.CityService;
import mk.ukim.finki.chartair.service.FlightService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final CityService cityService;

    public FlightServiceImpl(FlightRepository flightRepository, CityService cityService) {
        this.flightRepository = flightRepository;
        this.cityService = cityService;
    }

    @Override
    public List<Flight> listAll() {
        return this.flightRepository.findAll();
    }

    @Override
    public List<Flight> findAllByDepartureCity(Long departureId) {
        City city = this.cityService.findCityById(departureId);
        return this.flightRepository.findAllByDepartureCity(city);
    }

    @Override
    public List<Flight> findAllByDepartureAndArrivalCity(Long departureId, Long arrivalId) {
        if(departureId == null || arrivalId == null || departureId == 0 || arrivalId == 0)
            throw new InvalidArgumentsException();

        City departure = this.cityService.findCityById(departureId);
        City arrival = this.cityService.findCityById(arrivalId);
        return this.flightRepository.findAllByDepartureCityAndArrivalCity(departure, arrival);
    }

    @Override
    public Flight create(LocalDateTime departure, LocalDateTime arrival, City departureCity, City arrivalCity) {
        return this.flightRepository.save(new Flight(departure, arrival, departureCity, arrivalCity));
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return this.flightRepository.findById(id);
    }

}
