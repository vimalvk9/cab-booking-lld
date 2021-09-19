package com.vimal.cabbooking.entity;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Cab {
    private String name;
    private User driver;
    private boolean available;
    private Location location;
}
