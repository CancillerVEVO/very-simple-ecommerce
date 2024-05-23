package com.stellatech.elopezo.ecommerce.api.orders.dto;

import com.stellatech.elopezo.ecommerce.api.orders.Order;
import lombok.Builder;
import lombok.Data;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@Builder
public class OrderDto {
    private Long id;
    private Long userId;
    private String createdAt;

    public static OrderDto fromOrder(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .createdAt(order.getCreatedAt().toString())
                .build();
    }

    public static Iterable<OrderDto> fromIterable(Iterable<Order> orders) {
        return StreamSupport.stream(orders.spliterator(), false)
                .map(order -> OrderDto.builder()
                        .id(order.getId())
                        .userId(order.getUser().getId())
                        .createdAt(order.getCreatedAt().toString())
                        .build())
                .collect(Collectors.toList());
    }
}


