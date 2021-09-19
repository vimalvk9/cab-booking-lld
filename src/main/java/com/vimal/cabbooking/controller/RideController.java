package com.vimal.cabbooking.controller;

import com.vimal.cabbooking.entity.Cab;
import com.vimal.cabbooking.entity.Trip;
import com.vimal.cabbooking.entity.User;
import com.vimal.cabbooking.exception.RiderAlreadyExistsException;
import com.vimal.cabbooking.exception.RiderNotFoundException;
import com.vimal.cabbooking.manager.RideManager;
import com.vimal.cabbooking.manager.TripManager;
import com.vimal.cabbooking.request.BookCabReq;
import com.vimal.cabbooking.request.RegisterRiderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class RideController {

    @Autowired
    private RideManager rideManager;

    @Autowired
    private TripManager tripManager;


    @GetMapping(value = "riders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getAllRides() {
        return rideManager.getAllRides();
    }

    @GetMapping(value = "trips", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Trip>>  getAllTrips() {
        return tripManager.getAllTrips();
    }


    @PostMapping(value = "/register/rider", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User registerRider(@RequestBody RegisterRiderReq req) throws RiderAlreadyExistsException {
        return rideManager.registerRider(req);
    }

    @GetMapping(value = "/trip/ride-history",  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Trip> getUserRideHistory(@RequestParam String userId) {
        return tripManager.getUserRideHistory(userId);
    }

    @PostMapping(value = "/book/cab",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Trip bookCab(@RequestBody BookCabReq req) throws RiderNotFoundException {
        req.validate();
        return tripManager.bookCab(req);
    }


}
