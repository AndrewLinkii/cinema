package cinema.model.mapper;

import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.dto.request.MovieSessionRequestDto;
import cinema.model.dto.response.MovieSessionResponseDto;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponseDto toDto(MovieSession model) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setMovieId(model.getMovie().getId());
        dto.setCinemaHallId(model.getCinemaHall().getId());
        dto.setMovieSessionId(model.getId());
        dto.setMovieTitle(model.getMovie().getTitle());
        dto.setShowTime(model.getShowTime());
        return dto;
    }

    public MovieSession toModel(MovieSessionRequestDto dto) {
        MovieSession model = new MovieSession();
        Movie movie = movieService.getById(dto.getMovieId());
        CinemaHall cinemaHall = cinemaHallService.getById(dto.getCinemaHallId());
        model.setMovie(movie);
        model.setCinemaHall(cinemaHall);
        model.setShowTime(dto.getShowTime());
        return model;
    }
}
