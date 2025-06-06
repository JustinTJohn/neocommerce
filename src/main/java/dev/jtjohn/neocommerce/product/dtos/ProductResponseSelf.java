package dev.jtjohn.neocommerce.product.dtos;

import dev.jtjohn.neocommerce.product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseSelf {
    private Product product;
    private String message;
}
