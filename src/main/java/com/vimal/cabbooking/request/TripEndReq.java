package com.vimal.cabbooking.request;

import com.vimal.cabbooking.exception.BadRequestException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TripEndReq {
    private String cabName;
    private String riderId;
    private String tripName;

    public void validate() {

        List<String> errors = new ArrayList<>();
        if (this.cabName == null || this.cabName == "") {
            errors.add("cabName null | empty");
        }

        if (this.riderId == null || this.riderId == "") {
            errors.add("cabName null | empty");
        }
        if (this.tripName == null || this.tripName == "") {
            errors.add("cabName null | empty");
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(String.join(",", errors));
        }
    }
}
