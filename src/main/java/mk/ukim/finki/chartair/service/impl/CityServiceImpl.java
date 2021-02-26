package mk.ukim.finki.chartair.service.impl;

import mk.ukim.finki.chartair.model.City;
import mk.ukim.finki.chartair.model.Country;
import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.repository.CityRepository;
import mk.ukim.finki.chartair.repository.FlightRepository;
import mk.ukim.finki.chartair.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final FlightRepository flightRepository;

    public CityServiceImpl(CityRepository cityRepository, FlightRepository flightRepository) {
        this.cityRepository = cityRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public List<City> findAll(){
        return cityRepository.findAll();
    }

    @Override
    public List<City> findAllByDepartureCity(City city) {
        return flightRepository.findAllByDepartureCity(city)
                .stream().map(Flight::getArrivalCity).collect(Collectors.toList());
    }

    @Override
    public City create(String cityName, Country country, List<Flight> departingFlights, List<Flight> arrivingFlights) {
        return cityRepository.save(new City(cityName, country, departingFlights, arrivingFlights));
    }

}
