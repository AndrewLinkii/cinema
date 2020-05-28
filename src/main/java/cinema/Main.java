package cinema;

import cinema.exception.AuthenticationException;
import cinema.lib.Injector;
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

public class Main {
    private static Injector injector = Injector.getInstance("cinema");
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static UserService userService
            = (UserService) injector.getInstance(UserService.class);
    private static AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    public static void main(String[] args) throws AuthenticationException {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie movie1 = new Movie();
        movie1.setTitle("titan");
        movie1.setDescription("NORM");
        movieService.add(movie1);

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

        User user1 = authenticationService.register("lol@gmail", "ande", "123");

        shoppingCartService.addSession(movie1Session, user1);
        System.out.println(shoppingCartService.getByUser(user1));
        System.out.println(authenticationService.login("lol@gmail", "123"));
    }
}
