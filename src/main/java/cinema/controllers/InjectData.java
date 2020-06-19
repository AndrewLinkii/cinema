package cinema.controllers;

import cinema.exception.AuthenticationException;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectData {
    private final UserService userService;
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartService shoppingCartService;
    private final AuthenticationService authenticationService;

    public InjectData(UserService userService,
                      MovieService movieService,
                      CinemaHallService cinemaHallService,
                      MovieSessionService movieSessionService,
                      ShoppingCartService shoppingCartService,
                      AuthenticationService authenticationService) {
        this.userService = userService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartService = shoppingCartService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public void injectData() throws AuthenticationException {
        Movie movie1 = new Movie();
        movie1.setTitle("titan");
        movie1.setDescription("NORM");
        movieService.add(movie1);

        User user1 = authenticationService.register("lol@gmail", "ande");
        Movie movie2 = new Movie();
        movie2.setTitle("POLIKOP");
        movie2.setDescription("CULL");
        movieService.add(movie2);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(40);
        cinemaHall1.setDescription("red");
        cinemaHallService.add(cinemaHall1);

        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(30);
        cinemaHall2.setDescription("ble");
        cinemaHallService.add(cinemaHall2);

        MovieSession movie1Session = new MovieSession();
        movie1Session.setShowTime(LocalDateTime.of(2020, 6, 1, 8, 55));
        movie1Session.setMovie(movie1);
        movie1Session.setCinemaHall(cinemaHall1);
        movieSessionService.add(movie1Session);
    }
}
