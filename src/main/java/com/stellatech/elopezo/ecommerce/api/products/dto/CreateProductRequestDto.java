package com.stellatech.elopezo.ecommerce.api.products.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProductRequestDto {

    @NotBlank(message = "El campo name es requerido")
    private String name;
    @NotBlank(message = "El campo description es requerido")
    private String description;
    @NotBlank(message = "El campo image es requerido")
    private double price;
    @NotBlank(message = "El campo stock es requerido")
    private int stock;
}
