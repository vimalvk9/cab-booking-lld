package com.vimal.cabbooking.strategy;

import com.vimal.cabbooking.entity.Location;
import com.vimal.cabbooking.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class DefaultPriceCalculatingStrategy implements PriceCalculationStrategy{

    @Override
    public Double getCalculatedPrice(Location start, Location end) {
        Double diff = start.getDistance(end);
        return diff * Constants.PRICE_PER_KM;
    }
}
