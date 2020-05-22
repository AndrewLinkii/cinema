package cinema;

import cinema.lib.Injector;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("cinema");
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie movie1 = new Movie();
        movie1.setTitle("ABRACADABRA");
        movie1.setDescription("NORM");
        movieService.add(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("POLIKOP");
        movie2.setDescription("CULL");
        movieService.add(movie2);

        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(40);
        cinemaHall1.setDescription("1");
        cinemaHallService.add(cinemaHall1);

        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(30);
        cinemaHall2.setDescription("2");
        cinemaHallService.add(cinemaHall2);

        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movie1Session = new MovieSession();
        movie1Session.setShowTime(LocalDateTime.of(2020, 6, 1, 8, 55));
        movie1Session.setMovie(movie1);
        movie1Session.setCinemaHall(cinemaHall1);
        movieSessionService.add(movie1Session);

        movieSessionService.findAvailableSessions(movie1.getId(), LocalDateTime.of(2020, 6, 1, 2, 55 ))
                .forEach(System.out::println);
    }
}
