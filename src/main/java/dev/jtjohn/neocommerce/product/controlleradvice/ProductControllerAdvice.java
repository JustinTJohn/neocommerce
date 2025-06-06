package dev.jtjohn.neocommerce.product.controlleradvice;

import dev.jtjohn.neocommerce.product.dtos.ProductResponseSelf;
import dev.jtjohn.neocommerce.product.exceptions.ProductNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotPresentException.class)
    public ResponseEntity<ProductResponseSelf> handleInvalidProduct(){
        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null,
                "Product doesn't exist in controller advice.");
        return new ResponseEntity<>(
                productResponseSelf, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ProductResponseSelf> handleArithmeticException(){
        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null,
                "Error in controller advice.");
        return new ResponseEntity<>(
                productResponseSelf, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}