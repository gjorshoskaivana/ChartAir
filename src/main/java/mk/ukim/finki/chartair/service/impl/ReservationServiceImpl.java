package mk.ukim.finki.chartair.service.impl;

import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.model.Reservation;
import mk.ukim.finki.chartair.model.enumerations.TravelClass;
import mk.ukim.finki.chartair.repository.FlightRepository;
import mk.ukim.finki.chartair.repository.ReservationRepository;
import mk.ukim.finki.chartair.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, FlightRepository flightRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return this.reservationRepository.findById(id);
    }

    @Override
    public Reservation create(Integer numberOfBags, TravelClass travelClass, Integer numPassengers, Flight flight) {
        //Flight flight1 = this.flightRepository.findById(flight).get();
        return this.reservationRepository.save(new Reservation(numPassengers, flight, numberOfBags, travelClass));
    }

    @Override
    public void delete(Long id) {
        this.reservationRepository.deleteById(id);
    }
}
