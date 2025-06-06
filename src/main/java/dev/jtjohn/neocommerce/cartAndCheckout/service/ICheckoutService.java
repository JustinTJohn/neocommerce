package dev.jtjohn.neocommerce.cartAndCheckout.service;

import dev.jtjohn.neocommerce.cartAndCheckout.dtos.CheckoutItemRequestDto;
import dev.jtjohn.neocommerce.cartAndCheckout.dtos.StripeResponseDto;
import com.stripe.exception.StripeException;

import java.util.List;

public interface ICheckoutService {
    StripeResponseDto createSession(List<CheckoutItemRequestDto> checkoutItemDtoList) throws StripeException;
}