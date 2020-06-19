package cinema.controllers;

import cinema.model.dto.request.MovieRequestDto;
import cinema.model.dto.response.MovieResponseDto;
import cinema.model.mapper.MovieMapper;
import cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public void addMovie(@RequestBody @Valid MovieRequestDto movieRequestDto) {
        movieService.add(movieMapper.toModel(movieRequestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }
}
