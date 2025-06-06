package dev.jtjohn.neocommerce.product.services;

import dev.jtjohn.neocommerce.product.dtos.CategoryRequestDto;
import dev.jtjohn.neocommerce.product.exceptions.CategoryNotPresentException;
import dev.jtjohn.neocommerce.product.models.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);
}