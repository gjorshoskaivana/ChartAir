package mk.ukim.finki.chartair.repository;

import mk.ukim.finki.chartair.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
