package com.vimal.cabbooking.manager;

import com.vimal.cabbooking.entity.Cab;
import com.vimal.cabbooking.entity.Trip;
import com.vimal.cabbooking.exception.CabAlreadyExistsException;
import com.vimal.cabbooking.exception.CabNotFoundException;
import com.vimal.cabbooking.exception.RiderNotFoundException;
import com.vimal.cabbooking.exception.TripNotFoundException;
import com.vimal.cabbooking.request.RegisterCabReq;
import com.vimal.cabbooking.request.TripEndReq;
import com.vimal.cabbooking.request.UpdateCabAvailabilityReq;
import com.vimal.cabbooking.request.UpdateCabLocationReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CabManager {

    Map<String, Cab> cabs = new HashMap<>();

    @Autowired
    private TripManager tripManager;

    public Collection<Cab> getAllCabs() {
        return cabs.values();
    }

    public List<Cab> getAllAvailableCabs() {
        return cabs.values().stream().filter(Cab::isAvailable).collect(Collectors.toList());
    }

    public Cab registerCab(RegisterCabReq req) throws CabAlreadyExistsException {

        if (cabs.containsKey(req.getCabName())) {
            throw new CabAlreadyExistsException();
        }

        Cab cab = new Cab();
        cab.setName(req.getCabName());
        cab.setAvailable(true);
        cab.setDriver(req.getDriver());

        cabs.put(cab.getName(), cab);
        return cab;
    }

    public Cab updateCabLocation(UpdateCabLocationReq req) {
        checkCabExists(req.getCabName());

        Cab cab = cabs.get(req.getCabName());
        cab.setLocation(req.getLocation());
        return cab;
    }

    public Cab updateCabAvailability(UpdateCabAvailabilityReq req) {
        checkCabExists(req.getCabName());

        Cab cab = cabs.get(req.getCabName());
        cab.setAvailable(req.getAvailability());
        return cab;
    }



    public Trip endTrip(TripEndReq tripEndReq) throws TripNotFoundException {

        checkCabExists(tripEndReq.getCabName());

        Trip curTrip = Optional.ofNullable(tripManager.getUserRideHistory(tripEndReq.getRiderId()))
                .orElseGet(ArrayList::new)
                .stream()
                .filter(trip -> trip.getTripName().matches(tripEndReq.getTripName()))
                .findFirst()
                .orElse(null);

        if (curTrip == null) {
            throw new TripNotFoundException("trip not found");
        }

        curTrip.endTrip();
        return curTrip;
    }

    public void checkCabExists(String cabName) {
        if (!cabs.containsKey(cabName)) {
            throw new CabNotFoundException("cab does not exists");
        }
    }
}
