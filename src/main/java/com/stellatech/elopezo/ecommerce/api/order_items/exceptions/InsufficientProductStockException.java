package com.stellatech.elopezo.ecommerce.api.order_items.exceptions;

public class InsufficientProductStockException extends RuntimeException{
    public InsufficientProductStockException(String message) {
        super(message);
    }
}
