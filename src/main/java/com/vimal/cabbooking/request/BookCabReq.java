package com.vimal.cabbooking.request;

import com.vimal.cabbooking.entity.Location;
import com.vimal.cabbooking.exception.BadRequestException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookCabReq {
    private String tripName;
    private String userId;
    private Location start;
    private Location end;


    public void validate() {

        List<String> errors = new ArrayList<>();
        if (this.userId == null ) {
            errors.add("userId null | empty");
        }
        if (this.start == null || this.end == null) {
            errors.add("start | end null");
        }
        if (this.getTripName() == null || this.getTripName() == "") {
            errors.add("trip name null | empty");
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(String.join(", ", errors));
        }
    }
}
