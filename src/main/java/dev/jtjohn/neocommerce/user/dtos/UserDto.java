package dev.jtjohn.neocommerce.user.dtos;

import dev.jtjohn.neocommerce.cartAndCheckout.dtos.CartRequestDto;
import dev.jtjohn.neocommerce.order.dtos.OrderDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartRequestDto cart;
}
