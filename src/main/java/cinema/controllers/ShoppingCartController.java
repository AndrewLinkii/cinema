package cinema.controllers;

import cinema.model.User;
import cinema.model.dto.response.ShoppingCartResponseDto;
import cinema.model.mapper.ShoppingCartMapper;
import cinema.service.MovieSessionService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final MovieSessionService movieSessionService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(MovieSessionService movieSessionService,
                                  ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        User user = userService.findByEmail(((UserDetails) authentication.getPrincipal())
                .getUsername()).orElseThrow();
        return shoppingCartMapper.toDto(shoppingCartService.getByUser(user));
    }

    @PostMapping
    public void addMovieSession(Authentication authentication, @RequestParam Long movieSessionId) {
        User user = userService.findByEmail(((UserDetails) authentication.getPrincipal())
                .getUsername()).orElseThrow();
        shoppingCartService.addSession(movieSessionService.getById(movieSessionId), user);
    }
}
