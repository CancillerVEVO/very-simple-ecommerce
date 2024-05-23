package com.stellatech.elopezo.ecommerce.api.order_items.exceptions;

public class ItemAlredyInOrderException extends RuntimeException {
    public ItemAlredyInOrderException(String message)
    {
        super(message);
    }
}
