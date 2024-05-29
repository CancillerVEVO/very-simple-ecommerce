package com.stellatech.elopezo.ecommerce.api.orders;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(value = "Order.orderItems", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Order> findById(Long id);
}
