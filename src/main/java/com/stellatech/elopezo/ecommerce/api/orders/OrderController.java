package com.stellatech.elopezo.ecommerce.api.orders;

import com.stellatech.elopezo.ecommerce.api.orders.dto.OrderDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return orderService.getById(id, userId);
    }

    @GetMapping
    public Iterable<Order> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping
    public OrderDto addOrder(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return OrderDto.fromOrder(orderService.addOrder(userId));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id, HttpServletRequest request) {
            Long userId = (Long) request.getAttribute("userId");
            orderService.deleteOrder(id, userId);
    }

}
