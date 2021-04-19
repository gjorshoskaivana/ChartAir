package mk.ukim.finki.chartair.model;

import lombok.Data;
import mk.ukim.finki.chartair.model.enumerations.TravelClass;

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

    private TravelClass travelClass;

    private Integer numPassengers;

    public Reservation() {
    }

    public Reservation(Integer passengers, Flight flight, Integer numberOfBags, TravelClass travelClass) {
        this.numPassengers = passengers;
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
            price = 150.0 * numPassengers;
        else if(travelClass == TravelClass.BUSINESS)
            price = 50.0 * numPassengers;
        else if(travelClass == TravelClass.ECONOMY)
            price = 20.0 * numPassengers;
        return price;
    }
    public Reservation(Integer numberOfBags,TravelClass travelClass, Integer numPassengers) {
        this.numberOfBags = numberOfBags;
        this.numPassengers = numPassengers;
        this.priceOfReservation = computePrice(travelClass);
        this.travelClass=travelClass;
    }

}
