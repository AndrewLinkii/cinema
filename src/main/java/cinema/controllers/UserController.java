package cinema.controllers;

import cinema.model.dto.response.UserResponseDto;
import cinema.model.mapper.UserMapper;
import cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return userMapper.toDto(userService.findByEmail(email).get());
    }
}




