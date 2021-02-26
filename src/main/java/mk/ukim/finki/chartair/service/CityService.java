package mk.ukim.finki.chartair.service;

import mk.ukim.finki.chartair.model.City;
import mk.ukim.finki.chartair.model.Country;
import mk.ukim.finki.chartair.model.Flight;

import java.util.List;

public interface CityService {
    List<City> findAll();
    List<City> findAllByDepartureCity(City city);
    City create(String cityName, Country country, List<Flight> departingFlights, List<Flight> arrivingFlights);
}
