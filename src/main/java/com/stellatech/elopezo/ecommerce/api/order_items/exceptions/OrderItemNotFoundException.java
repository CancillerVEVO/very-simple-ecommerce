package com.stellatech.elopezo.ecommerce.api.order_items.exceptions;

public class OrderItemNotFoundException extends  RuntimeException{
    public OrderItemNotFoundException(String message) {
        super(message);
    }
}
