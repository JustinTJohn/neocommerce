package dev.jtjohn.neocommerce.cartAndCheckout.dtos;

import dev.jtjohn.neocommerce.product.dtos.ProductDto;

import java.math.BigDecimal;

public class CartItemRequestDto {
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDto product;
}
