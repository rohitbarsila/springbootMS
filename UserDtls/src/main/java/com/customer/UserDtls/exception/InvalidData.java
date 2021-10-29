package com.customer.UserDtls.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED, reason = "Invalid Email Id")
public class InvalidData extends Exception {

    public InvalidData() {
        super();
    }

    public InvalidData(String message) {
        super(message);
    }

}

