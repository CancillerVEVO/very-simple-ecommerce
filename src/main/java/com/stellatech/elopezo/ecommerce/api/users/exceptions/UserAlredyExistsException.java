package com.stellatech.elopezo.ecommerce.api.users.exceptions;

public class UserAlredyExistsException extends RuntimeException{
    public UserAlredyExistsException(String message) {
        super(message);
    }
}
