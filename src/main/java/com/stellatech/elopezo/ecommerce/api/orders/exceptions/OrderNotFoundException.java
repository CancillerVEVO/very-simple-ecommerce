package com.stellatech.elopezo.ecommerce.api.orders.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
