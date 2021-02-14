package mk.ukim.finki.chartair.repository;

import mk.ukim.finki.chartair.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
