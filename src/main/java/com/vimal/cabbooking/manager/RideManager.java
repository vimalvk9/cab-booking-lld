package com.vimal.cabbooking.manager;

import com.vimal.cabbooking.entity.Cab;
import com.vimal.cabbooking.entity.User;
import com.vimal.cabbooking.exception.RiderAlreadyExistsException;
import com.vimal.cabbooking.request.RegisterRiderReq;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RideManager {

    Map<String, User> riders = new HashMap<>();

    public Collection<User> getAllRides() {
        return riders.values();
    }

    public User registerRider(RegisterRiderReq registerRiderReq) throws RiderAlreadyExistsException {

        if (riders.containsKey(registerRiderReq.getId())) {
            throw new RiderAlreadyExistsException();
        }

        User rider = new User(registerRiderReq.getId(), registerRiderReq.getName());
        riders.put(registerRiderReq.getId(), rider);
        return rider;
    }

}
