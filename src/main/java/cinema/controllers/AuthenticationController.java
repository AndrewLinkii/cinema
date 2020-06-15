package cinema.controllers;


import cinema.exception.AuthenticationException;
import cinema.model.dto.request.UserRequestDto;
import cinema.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public void register(@RequestBody UserRequestDto userRequestDto) {
        try {
            authenticationService.register(
                    userRequestDto.getEmail(),
                    userRequestDto.getLogin(),
                    userRequestDto.getPassword());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}