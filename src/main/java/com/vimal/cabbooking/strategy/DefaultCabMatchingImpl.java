package com.vimal.cabbooking.strategy;

import com.vimal.cabbooking.entity.Cab;
import com.vimal.cabbooking.entity.Location;
import com.vimal.cabbooking.manager.CabManager;
import com.vimal.cabbooking.manager.TripManager;
import com.vimal.cabbooking.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefaultCabMatchingImpl implements CabMatchingStrategy{


    @Autowired
    private CabManager cabManager;


    @Override
    public Optional<Cab> getOptimalCab(Location start) {

        List<Cab> allAvailableCabs = cabManager.getAllAvailableCabs();

        return Optional.ofNullable(allAvailableCabs)
                .orElseGet(ArrayList::new)
                .stream()
                .filter(cab -> Objects.nonNull(cab.getLocation()))
                .filter(cab -> {
                    Double diff = cab.getLocation().getDistance(start);
                    return diff <= Constants.MAX_RIDER_DISTANCE_TRAVEL_RANGE;
                })
                .findAny();
    }
}
