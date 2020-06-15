package cinema.model.mapper;

import cinema.model.Movie;
import cinema.model.dto.request.MovieRequestDto;
import cinema.model.dto.response.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieResponseDto toDto(Movie model) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setMovieId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());
        return dto;
    }

    public Movie toModel(MovieRequestDto dto) {
        Movie model = new Movie();
        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        return model;
    }
}
