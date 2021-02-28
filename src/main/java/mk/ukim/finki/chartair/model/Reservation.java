package mk.ukim.finki.chartair.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long reservationId;

    @OneToMany(mappedBy = "reservation")
    private List<Passenger> passengers;

    @OneToOne
    private Flight flight;

    private Integer numberOfBags;

    private Double priceOfReservation;

    public Reservation() {
    }

    public Reservation(List<Passenger> passengers, Flight flight, Integer numberOfBags, TravelClass travelClass) {
        this.passengers = passengers;
        this.flight = flight;
        this.numberOfBags = numberOfBags;
        this.priceOfReservation = computePrice(travelClass);
    }

    /*
    fixed price for a bag = $50
     */

    private Double computePrice(TravelClass travelClass){
        double price = 0.0;
        if(travelClass == TravelClass.FIRST_CLASS)
            price = 150.0*passengers.size() + 50.0*numberOfBags;
        else if(travelClass == TravelClass.BUSINESS)
            price = 50.0*passengers.size() + 50.0*numberOfBags;
        else if(travelClass == TravelClass.ECONOMY)
            price = 20.0*passengers.size() + 50.0*numberOfBags;
        return price;
    }

}
