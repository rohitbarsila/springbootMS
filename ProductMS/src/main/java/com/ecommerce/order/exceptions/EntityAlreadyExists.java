package com.ecommerce.order.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED, reason = "Entity Already Exists")
public class EntityAlreadyExists extends Exception {

    public EntityAlreadyExists() {
        super();
    }

    public EntityAlreadyExists(String message) {
        super(message);
    }

}

