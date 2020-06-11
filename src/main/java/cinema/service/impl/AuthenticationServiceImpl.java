package cinema.service.impl;

import cinema.exception.AuthenticationException;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import cinema.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private static UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        return userService.findByEmail(email).filter(u -> u.getPassword()
                .equals(HashUtil.hashPassword(password, u.getSalt())))
                .orElseThrow(() -> new AuthenticationException("incorrect email or password!"));
    }

    @Override
    public User register(String email, String login, String password)
            throws AuthenticationException {
        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(password, user.getSalt()));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
