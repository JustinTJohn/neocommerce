package dev.jtjohn.neocommerce.order.services;

import dev.jtjohn.neocommerce.order.dtos.OrderDto;
import dev.jtjohn.neocommerce.order.models.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getOrdersByUserId(Long userId);

    OrderDto convertToDto(Order order);
}
