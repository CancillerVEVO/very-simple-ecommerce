package com.stellatech.elopezo.ecommerce.api.orders.exceptions;

public class OrderPermissionException extends RuntimeException{
    public OrderPermissionException(String message) {
        super(message);
    }
}
