package cinema.model.mapper;

import cinema.model.Ticket;
import cinema.model.dto.response.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto toDto(Ticket model) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setTicketId(model.getId());
        dto.setCinemaHallDescription(model
                .getMovieSession().getCinemaHall().getDescription());
        dto.setMovieTitle(model.getMovieSession().getMovie().getTitle());
        dto.setShowTime(model.getMovieSession().getShowTime());
        return dto;
    }
}
