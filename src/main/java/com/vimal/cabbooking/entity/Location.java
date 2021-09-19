package com.vimal.cabbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.lang.Math;

@Getter
@ToString
@AllArgsConstructor
public class Location {

    private Double fromPoint;
    private Double toPoint;

    public Double getDistance(@NonNull final Location location) {
        double xDiff = Math.pow(Math.abs(this.fromPoint - location.fromPoint), 2);
        double yDiff = Math.pow(Math.abs(this.toPoint - location.toPoint), 2);
        return Math.sqrt( xDiff + yDiff );
    }
}
