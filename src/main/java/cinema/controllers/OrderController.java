package cinema.controllers;

import cinema.model.User;
import cinema.model.dto.request.OrderRequestDto;
import cinema.model.dto.response.OrderResponseDto;
import cinema.model.mapper.OrderMapper;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderMapper orderMapper, OrderService orderService,
                           UserService userService, ShoppingCartService shoppingCartService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public void completeOrder(@RequestBody @Valid OrderRequestDto order) {
        User user = userService.getById(order.getUserId());
        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders(Authentication authentication) {
        User user = userService.findByEmail(((UserDetails) authentication.getPrincipal())
                .getUsername()).orElseThrow();
        return orderService.getOrderHistory(user).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
