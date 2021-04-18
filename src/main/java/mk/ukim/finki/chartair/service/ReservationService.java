package mk.ukim.finki.chartair.service;

import mk.ukim.finki.chartair.model.*;
import mk.ukim.finki.chartair.model.enumerations.TravelClass;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    Reservation create(Integer numberOfBags, Double priceOfReservation, TravelClass travelClass);


}
