package com.stellatech.elopezo.ecommerce.api.order_items.dto;

import com.stellatech.elopezo.ecommerce.api.order_items.OrderItems;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Data
@Builder
public class OrderItemsDto {
    private Long orderId;
    private Long productId;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private String createdAt;

    public static OrderItemsDto fromEntity(OrderItems orderItems) {
        return OrderItemsDto.builder()
                .orderId(orderItems.getId().getOrderId())
                .name(orderItems.getProduct().getName())
                .description(orderItems.getProduct().getDescription())
                .productId(orderItems.getId().getProductId())
                .quantity(orderItems.getQuantity())
                .price(orderItems.getPrice())
                .createdAt(orderItems.getCreatedAt().toString())
                .build();
    }

    public static Iterable<OrderItemsDto> fromIterable(Iterable<OrderItems> orderItems) {
        return StreamSupport.stream(orderItems.spliterator(), false)
                .map(OrderItemsDto::fromEntity)
                .collect(Collectors.toList());
    }
}
