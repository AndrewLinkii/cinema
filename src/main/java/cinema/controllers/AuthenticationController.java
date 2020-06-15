package cinema.controllers;

import cinema.model.dto.request.UserRequestDto;
import cinema.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping("/register")
    @PostMapping
    public void register(@RequestBody UserRequestDto userRequestDto) {
        authenticationService.register(
                userRequestDto.getEmail(),
                userRequestDto.getLogin(),
                userRequestDto.getPassword());
    }
}
