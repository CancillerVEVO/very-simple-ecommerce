package com.stellatech.elopezo.ecommerce.api.order_items;

import com.stellatech.elopezo.ecommerce.api.orders.Order;
import com.stellatech.elopezo.ecommerce.api.products.Product;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OrderItems {
    @EmbeddedId
    OrderItemsKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(
            name = "order_id",
            foreignKey = @ForeignKey(name = "fk_order_items_orders")
    )
    Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "fk_order_items_products")
    )
    Product product;

    @Column(name = "quantity")
    Integer quantity;

    @Column
    Double price;

    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP",
            insertable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            insertable = false
    )
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            insertable = false
    )
    private LocalDateTime deletedAt;
}
