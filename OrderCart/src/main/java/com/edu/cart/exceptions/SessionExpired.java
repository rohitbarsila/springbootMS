package com.edu.cart.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Token Expired !")
public class SessionExpired extends Exception {

    public SessionExpired() {
        super();
    }

    public SessionExpired(String message) {
        super(message);
    }

}

