package com.stellatech.elopezo.ecommerce.api.order_items.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOrderItemRequestDto {
   private Integer productId;
   private Integer quantity;

}
