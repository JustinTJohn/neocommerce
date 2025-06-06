package dev.jtjohn.neocommerce.product.dtos;

import dev.jtjohn.neocommerce.product.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponseSelf {
    private Category category;
    private String message;
}
