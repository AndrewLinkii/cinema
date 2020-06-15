package cinema.model.mapper;


import cinema.model.dto.request.CinemaHallRequestDto;
import cinema.model.dto.response.CinemaHallResponseDto;
import cinema.model.CinemaHall;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    public CinemaHallResponseDto toDto(CinemaHall model) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setCinemaHallId(model.getId());
        dto.setCapacity(model.getCapacity());
        dto.setDescription(model.getDescription());
        return dto;
    }

    public CinemaHall toModel(CinemaHallRequestDto dto) {
        CinemaHall model = new CinemaHall();
        model.setCapacity(dto.getCapacity());
        model.setDescription(dto.getDescription());
        return model;
    }
}
