package com.vimal.cabbooking.request;

import com.vimal.cabbooking.exception.BadRequestException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateCabAvailabilityReq {
    private String cabName;
    private Boolean availability;


    public void validate() {

        List<String> errors = new ArrayList<>();
        if (this.cabName == null || this.cabName == "") {
            errors.add("cabName null | empty");
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(String.join(",", errors));
        }
    }
}
