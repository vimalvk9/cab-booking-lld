package com.vimal.cabbooking.controller;

import com.vimal.cabbooking.entity.Cab;
import com.vimal.cabbooking.entity.Trip;
import com.vimal.cabbooking.exception.CabAlreadyExistsException;
import com.vimal.cabbooking.exception.TripNotFoundException;
import com.vimal.cabbooking.manager.CabManager;
import com.vimal.cabbooking.manager.TripManager;
import com.vimal.cabbooking.request.RegisterCabReq;
import com.vimal.cabbooking.request.TripEndReq;
import com.vimal.cabbooking.request.UpdateCabAvailabilityReq;
import com.vimal.cabbooking.request.UpdateCabLocationReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CabController {

    @Autowired
    private CabManager cabManager;

    @Autowired
    private TripManager tripManager;

    @GetMapping(value = "cabs",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Cab> getAlCabs() {
        return cabManager.getAllCabs();
    }

    @PostMapping(value = "/register/cab",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Cab registerCab(@RequestBody RegisterCabReq req) throws CabAlreadyExistsException {
        req.validate();
        return cabManager.registerCab(req);
    }

    @PutMapping(value = "/update/cab-location",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Cab updateCabLocation(@RequestBody UpdateCabLocationReq req) {
        req.validate();
        return cabManager.updateCabLocation(req);
    }

    @PutMapping(value = "/update/cab-availability",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Cab updateCabAvailability(@RequestBody UpdateCabAvailabilityReq req) {
        req.validate();
        return cabManager.updateCabAvailability(req);
    }

    @PutMapping(value = "/trip/end", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Trip endTrip(@RequestBody TripEndReq tripEndReq) throws TripNotFoundException {
        return cabManager.endTrip(tripEndReq);
    }
}
