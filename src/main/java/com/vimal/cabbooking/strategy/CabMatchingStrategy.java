package com.vimal.cabbooking.strategy;

import com.vimal.cabbooking.entity.Cab;
import com.vimal.cabbooking.entity.Location;

import java.util.Optional;

public interface CabMatchingStrategy {

    public Optional<Cab> getOptimalCab(Location start);
}
