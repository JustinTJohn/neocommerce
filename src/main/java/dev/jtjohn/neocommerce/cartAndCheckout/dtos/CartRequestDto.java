package dev.jtjohn.neocommerce.cartAndCheckout.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartRequestDto {
    private Long cartId;
    private Set<CartItemRequestDto> items;
    private BigDecimal totalAmount;
}