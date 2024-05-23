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
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping
    public Iterable<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping
    public OrderDto addOrder(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return orderService.addOrder(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id, HttpServletRequest request) {
            Long userId = (Long) request.getAttribute("userId");
            orderService.deletOrder(id, userId);
    }

}
