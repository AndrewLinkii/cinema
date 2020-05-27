package cinema.service;

import cinema.exception.AuthenticationException;
import cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String login, String password) throws AuthenticationException;
}
