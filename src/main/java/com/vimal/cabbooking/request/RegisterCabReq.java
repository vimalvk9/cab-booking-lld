package com.vimal.cabbooking.request;

import com.vimal.cabbooking.entity.User;
import com.vimal.cabbooking.exception.BadRequestException;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class RegisterCabReq implements Serializable {
    private String cabName;
    private User driver;
    private boolean available = true;


    public void validate() {

        List<String> errors = new ArrayList<>();
        if (this.cabName == null || this.cabName == "") {
            errors.add("cabName null | empty");
        }
        if (this.driver == null) {
            errors.add("driver is null");
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(String.join(",", errors));
        }
    }
}
