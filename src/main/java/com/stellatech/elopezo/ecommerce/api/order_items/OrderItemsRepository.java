package com.stellatech.elopezo.ecommerce.api.order_items;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, OrderItemsKey> {
    Iterable<OrderItems> findByOrderId(Long orderId);
}
