package mk.ukim.finki.chartair.service.impl;

import mk.ukim.finki.chartair.model.Flight;
import mk.ukim.finki.chartair.model.Passenger;
import mk.ukim.finki.chartair.model.Reservation;
import mk.ukim.finki.chartair.model.TravelClass;
import mk.ukim.finki.chartair.repository.ReservationRepository;
import mk.ukim.finki.chartair.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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
    public Reservation create(Integer numberOfBags, Double price, TravelClass travelClass) {
        return this.reservationRepository.save(new Reservation(numberOfBags, price, travelClass));
    }
}
