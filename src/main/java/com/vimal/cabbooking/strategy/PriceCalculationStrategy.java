package com.vimal.cabbooking.strategy;

import com.vimal.cabbooking.entity.Location;
import org.springframework.stereotype.Service;

@Service
public interface PriceCalculationStrategy {

    public Double getCalculatedPrice(Location start, Location end);
}
