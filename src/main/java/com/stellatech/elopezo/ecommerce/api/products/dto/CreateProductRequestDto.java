package com.stellatech.elopezo.ecommerce.api.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProductRequestDto {

    @NotBlank(message = "El campo name es requerido")
    private String name;
    @NotBlank(message = "El campo description es requerido")
    private String description;
    @NotNull(message = "El campo price es requerido")
    private double price;
    @NotNull(message = "El campo stock es requerido")
    private int stock;


}
