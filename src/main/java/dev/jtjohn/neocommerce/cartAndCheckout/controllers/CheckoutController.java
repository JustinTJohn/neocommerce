package dev.jtjohn.neocommerce.cartAndCheckout.controllers;

import dev.jtjohn.neocommerce.cartAndCheckout.dtos.CheckoutItemRequestDto;
import dev.jtjohn.neocommerce.cartAndCheckout.dtos.StripeResponseDto;
import dev.jtjohn.neocommerce.cartAndCheckout.service.CheckoutService;
import dev.jtjohn.neocommerce.utils.dtos.ApiResponse;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api_prefix}/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    /**
     * Endpoint to create a payment session for the provided checkout items.
     *
     * @param checkoutItemDtoList List of items for the checkout session
     * @return ResponseEntity containing ApiResponse with Stripe session details or an error message
     */
    @PostMapping("/create-session")
    public ResponseEntity<ApiResponse> createCheckoutSession(@RequestBody List<CheckoutItemRequestDto> checkoutItemDtoList) {
        try {
            // Create a Stripe checkout session
            StripeResponseDto stripeResponse = checkoutService.createSession(checkoutItemDtoList);
            return ResponseEntity.ok(new ApiResponse("Checkout session created successfully.!", stripeResponse));
        } catch (StripeException stripeException) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Stripe error: " + stripeException.getMessage(), null));
        } catch (Exception exception) {
            // Handle unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            new ApiResponse("An unexpected error occurred: " +  exception.getMessage(), null)
                    );
        }
    }
}
