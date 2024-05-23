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

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();

    }

    public Order getById(Long id, Long userId) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Orden no encontrada"));
        if (!order.getUser().getId().equals(userId)) {
            throw new OrderPermissionException("No tienes permiso para ver esta orden");
        }
        return order;
    }
      public Order addOrder(Long userId) {
        User user = userService.getById(userId);
        return orderRepository.save(Order.builder()
                .user(user)
                .build());
    }

    public void deleteOrder(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Orden no encontrada"));
        if (!order.getUser().getId().equals(userId)) {
            throw new OrderPermissionException("No tienes permiso para eliminar esta orden");
        }
        orderRepository.delete(order);
    }

}
