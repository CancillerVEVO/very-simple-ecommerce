package com.stellatech.elopezo.ecommerce.api.auth.exceptions;

public class UserAuthenticationException extends RuntimeException{
    public UserAuthenticationException(String message) {
        super(message);
    }
}
