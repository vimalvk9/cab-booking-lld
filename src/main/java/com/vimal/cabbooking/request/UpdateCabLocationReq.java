package com.vimal.cabbooking.request;

import com.vimal.cabbooking.entity.Location;
import com.vimal.cabbooking.exception.BadRequestException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateCabLocationReq {
    private String cabName;
    private Location location;

    public void validate() {

        List<String> errors = new ArrayList<>();
        if (this.cabName == null || this.cabName == "") {
            errors.add("cabName null | empty");
        }
        if (this.location == null) {
            errors.add("location is null");
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(String.join(",", errors));
        }
    }
}
