package dev.jtjohn.neocommerce.order.controllers;

import dev.jtjohn.neocommerce.utils.dtos.ApiResponse;
import dev.jtjohn.neocommerce.utils.exceptions.ResourceNotFoundException;
import dev.jtjohn.neocommerce.order.dtos.OrderDto;
import dev.jtjohn.neocommerce.order.models.Order;
import dev.jtjohn.neocommerce.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api_prefix}/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId) {
        try {
            Order order = orderService.placeOrder(userId);
            OrderDto orderDto = orderService.convertToDto(order);
            return ResponseEntity.ok(new ApiResponse("Order created successfully!", orderDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Error Occurred", e.getMessage()));
        }
    }

    @GetMapping("/{orderId}/order")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId) {
        try {
            OrderDto order = orderService.getOrder(orderId);
            return ResponseEntity.ok(new ApiResponse("Order fetched successfully!", order));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse("Oops!", e.getMessage()));
        }
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<ApiResponse> getOrdersByUserId(@PathVariable Long userId) {
        try {
            List<OrderDto> order = orderService.getOrdersByUserId(userId);
            return ResponseEntity.ok(new ApiResponse("Order fetched successfully!", order));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse("Oops!", e.getMessage()));
        }
    }

}