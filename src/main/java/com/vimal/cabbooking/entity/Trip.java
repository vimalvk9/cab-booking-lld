package com.vimal.cabbooking.entity;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Trip {

    private String tripName;
    private Cab cab;
    private Double price;
    private User driver;
    private User rider;
    private Location start;
    private Location end;
    private TripStatus tripStatus;

    public Trip(String tripName, Cab cab, Double price, User driver, User rider, Location start, Location end) {
        this.tripName = tripName;
        this.cab = cab;
        this.price = price;
        this.driver = driver;
        this.rider = rider;
        this.start = start;
        this.end = end;
        this.tripStatus = TripStatus.IN_PROGRESS;
    }

    public void endTrip() {
        this.tripStatus = TripStatus.ENDED;
    }

    public static enum TripStatus {
        IN_PROGRESS,
        ENDED
    }
}
