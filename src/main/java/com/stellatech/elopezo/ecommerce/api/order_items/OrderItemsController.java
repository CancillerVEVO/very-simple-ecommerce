package com.stellatech.elopezo.ecommerce.api.order_items;

import com.stellatech.elopezo.ecommerce.api.order_items.dto.CreateOrderItemRequestDto;
import com.stellatech.elopezo.ecommerce.api.order_items.dto.OrderItemsDto;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderItemsController {
    private final OrderItemsService orderItemsService;
    private final static Logger logger = LoggerFactory.getLogger(OrderItemsController.class);
    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping("/orders/{orderId}/items")
    public Iterable<OrderItemsDto> getOrderItemsByOrderId(@PathVariable Long orderId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return OrderItemsDto.fromIterable(orderItemsService.getOrderItems(orderId, userId));
    }

    @PostMapping("/orders/{orderId}/items")
    public OrderItemsDto addOrderItem(@RequestBody CreateOrderItemRequestDto createOrderItemRequestDto, @PathVariable Long orderId, HttpServletRequest request) {
        logger.info("Adding order item to order with id: " + orderId);
        logger.info("Request: " + createOrderItemRequestDto);
        Long userId = (Long) request.getAttribute("userId");
        return OrderItemsDto.fromEntity(orderItemsService.addOrderItem(createOrderItemRequestDto, orderId, userId));
    }

}
