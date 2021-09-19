package com.vimal.cabbooking.manager;

import com.google.gson.Gson;
import com.vimal.cabbooking.entity.Cab;
import com.vimal.cabbooking.entity.Location;
import com.vimal.cabbooking.entity.Trip;
import com.vimal.cabbooking.entity.User;
import com.vimal.cabbooking.exception.BadRequestException;
import com.vimal.cabbooking.exception.CabNotFoundException;
import com.vimal.cabbooking.exception.RiderNotFoundException;
import com.vimal.cabbooking.request.BookCabReq;
import com.vimal.cabbooking.strategy.DefaultCabMatchingImpl;
import com.vimal.cabbooking.strategy.PriceCalculationStrategy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TripManager {

    private Map<String, List<Trip>> userTrips = new HashMap<>();

    private RideManager rideManager;
    private DefaultCabMatchingImpl cabMatchingImpl;
    private PriceCalculationStrategy priceCalculationStrategy;
    private static final Gson gson = new Gson();

    public TripManager(DefaultCabMatchingImpl cabMatchingImpl, PriceCalculationStrategy priceCalculationStrategy, RideManager rideManager) {
        this.cabMatchingImpl = cabMatchingImpl;
        this.priceCalculationStrategy = priceCalculationStrategy;
        this.rideManager = rideManager;
    }

    public Map<String, List<Trip>> getAllTrips() {
        return userTrips;
    }

    public Trip bookCab(BookCabReq req) throws RiderNotFoundException {

        if (!rideManager.riders.containsKey(req.getUserId())) {
            throw new BadRequestException("rider not found");
        }

        User rider = rideManager.riders.get(req.getUserId());
        System.out.println("Rider : " + gson.toJson(rider));
        if (rider == null) {
            throw new RiderNotFoundException("not a valid rider");
        }
        // get start and end locations
        Location start = req.getStart();
        Location end = req.getEnd();

        // get optimal cab
        Optional<Cab> cab =  cabMatchingImpl.getOptimalCab(start);
        if (!cab.isPresent()) {
            throw new CabNotFoundException("no cabs found !");
        }
        System.out.println("Cab " + gson.toJson(cab.get()));

        // get price
        Double price = priceCalculationStrategy.getCalculatedPrice(start, end);
        System.out.println("Price is " + price);

        // create trip
        List<Trip> trips = userTrips.get(req.getUserId());
        System.out.println("Trips is " + gson.toJson(trips));

        if (trips == null) {
            trips = new ArrayList<>();
        }

        Trip curTrip = new Trip();
        curTrip.setTripName(req.getTripName());
        curTrip.setCab(cab.get());
        curTrip.setPrice(price);
        curTrip.setDriver(cab.get().getDriver());
        curTrip.setRider(rider);
        curTrip.setTripStatus(Trip.TripStatus.IN_PROGRESS);
        trips.add(curTrip);

        userTrips.put(req.getUserId(), trips);
        return curTrip;
    }

    public List<Trip> getUserRideHistory(String userId) {
        if (!userTrips.containsKey(userId)) {
            throw new BadRequestException("user does not have any trips");
        }

        return userTrips.get(userId);
    }

}
