package com.stellatech.elopezo.ecommerce.api.orders;


import com.stellatech.elopezo.ecommerce.api.orders.dto.OrderDto;
import com.stellatech.elopezo.ecommerce.api.orders.exceptions.OrderNotFoundException;
import com.stellatech.elopezo.ecommerce.api.orders.exceptions.OrderPermissionException;
import com.stellatech.elopezo.ecommerce.api.users.User;
import com.stellatech.elopezo.ecommerce.api.users.UserService;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public Iterable<OrderDto> getOrders() {
        return OrderDto.fromIterable(orderRepository.findAll());

    }

    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order no encontrada"));
        return OrderDto.fromOrder(order);
    }

    public OrderDto addOrder(Long userId) {
        User user = userService.getById(userId);
        Order newOrder = Order.builder()
                .user(user)
                .build();

        return OrderDto.fromOrder(orderRepository.save(newOrder));
    }

    public void deletOrder(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Orden no encontrada"));
        if (!order.getUser().getId().equals(userId)) {
            throw new OrderPermissionException("No tienes permiso para eliminar esta orden");
        }
        orderRepository.delete(order);
    }

}
