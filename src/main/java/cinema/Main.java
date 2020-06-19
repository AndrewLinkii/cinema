package cinema;

import cinema.config.AppConfig;
import cinema.exception.AuthenticationException;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import java.time.LocalDateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws AuthenticationException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);
        MovieService movieService = context.getBean(MovieService.class);;

        Movie movie1 = new Movie();
        movie1.setTitle("titan");
        movie1.setDescription("NORM");
        movieService.add(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("POLIKOP");
        movie2.setDescription("CULL");
        movieService.add(movie2);

        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);

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
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movie1Session);

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        User user1 = authenticationService.register("lol@gmail", "ande");

        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(movie1Session, user1);
        System.out.println(shoppingCartService.getByUser(user1));
        System.out.println(authenticationService.login("lol@gmail", "123"));

        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user1).getTickets(),user1);
    }
}
