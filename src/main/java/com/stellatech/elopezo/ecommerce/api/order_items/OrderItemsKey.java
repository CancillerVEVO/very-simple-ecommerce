package com.stellatech.elopezo.ecommerce.api.order_items;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class OrderItemsKey implements Serializable {
    @Column(name= "order_id")
    Long orderId;

    @Column(name= "product_id")
    Long productId;
}
