package com.stellatech.elopezo.ecommerce.api.products.dto;

import com.stellatech.elopezo.ecommerce.api.products.Product;
import lombok.Builder;
import lombok.Data;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@Builder
public class ProductDetailResponseDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long userId;
    private String createdAt;

    public static Iterable<ProductDetailResponseDto> fromIterable(Iterable<Product> products) {
        return StreamSupport.stream(products.spliterator(), false)
                .map(product -> ProductDetailResponseDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .userId(product.getUser().getId())
                        .createdAt(product.getCreatedAt().toString())
                        .build())
                .collect(Collectors.toList());
    }

    public static ProductDetailResponseDto fromProduct(Product product) {
        return ProductDetailResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .userId(product.getUser().getId())
                .createdAt(product.getCreatedAt().toString())
                .build();
    }
}
