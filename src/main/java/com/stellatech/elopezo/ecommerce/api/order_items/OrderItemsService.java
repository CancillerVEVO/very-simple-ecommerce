package com.stellatech.elopezo.ecommerce.api.order_items;

import com.stellatech.elopezo.ecommerce.api.order_items.dto.CreateOrderItemRequestDto;
import com.stellatech.elopezo.ecommerce.api.order_items.dto.OrderItemsDto;
import com.stellatech.elopezo.ecommerce.api.order_items.dto.UpdateOrderItemRequestDto;
import com.stellatech.elopezo.ecommerce.api.order_items.exceptions.InsufficientProductStockException;
import com.stellatech.elopezo.ecommerce.api.order_items.exceptions.ItemAlredyInOrderException;
import com.stellatech.elopezo.ecommerce.api.order_items.exceptions.OrderItemNotFoundException;
import com.stellatech.elopezo.ecommerce.api.orders.Order;
import com.stellatech.elopezo.ecommerce.api.orders.OrderService;
import com.stellatech.elopezo.ecommerce.api.products.Product;
import com.stellatech.elopezo.ecommerce.api.products.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;
    private final OrderService orderService;
    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(OrderItemsService.class);

    public OrderItemsService(OrderItemsRepository orderItemsRepository, OrderService orderService, ProductService productService) {
        this.orderItemsRepository = orderItemsRepository;
        this.orderService = orderService;
        this.productService = productService;
    }

    public Iterable<OrderItems> getOrderItems(Long orderId, Long userId) {
        orderService.getById(orderId, userId);
        return orderItemsRepository.findByOrderId(orderId);

    }

    public OrderItems addOrderItem(CreateOrderItemRequestDto createOrderItemRequestDto, Long orderId,Long userId) {
        Order order = orderService.getById(orderId, userId);
        Product product = productService.getById(Long.valueOf(createOrderItemRequestDto.getProductId()));

        Integer productStock = product.getStock();


        if (productStock < createOrderItemRequestDto.getQuantity()) {
            throw new InsufficientProductStockException("El producto no tiene suficiente stock para la cantidad solicitada");
        }

       OrderItemsKey orderItemsKey = new OrderItemsKey(order.getId(), product.getId());

        if (orderItemsRepository.findById(orderItemsKey).isPresent()) {
            throw new ItemAlredyInOrderException("El producto ya está en la orden");
        }


        OrderItems orderItem = new OrderItems(
                orderItemsKey,
                order,
                product,
                createOrderItemRequestDto.getQuantity(),
                createOrderItemRequestDto.getQuantity() * product.getPrice()

        );

        return orderItemsRepository.save(orderItem);
    }

    public void deleteOrderItem(Long orderId, Long productId, Long userId) {
        Order order = orderService.getById(orderId, userId);
        Product product = productService.getById(productId);
        OrderItems orderItem = orderItemsRepository.findById(new OrderItemsKey(order.getId(), product.getId()))
                .orElseThrow(() -> new OrderItemNotFoundException("Item de orden no encontrado"));

        orderItemsRepository.delete(orderItem);
    }

    public OrderItems updateOrderItem(UpdateOrderItemRequestDto updateOrderItemRequestDto, Long orderId, Long productId, Long userId) {
        Order order = orderService.getById(orderId, userId);
        Product product = productService.getById(productId);
        OrderItems orderItem = orderItemsRepository.findById(new OrderItemsKey(order.getId(), product.getId()))
                .orElseThrow(() -> new OrderItemNotFoundException("Item de orden no encontrado"));

        if (updateOrderItemRequestDto.getQuantity() > product.getStock()) {
            throw new InsufficientProductStockException("El producto no tiene suficiente stock para la cantidad solicitada");
        }

        orderItem.setQuantity(updateOrderItemRequestDto.getQuantity());
        orderItem.setPrice(updateOrderItemRequestDto.getQuantity() * product.getPrice());
        return orderItemsRepository.save(orderItem);
    }
}
