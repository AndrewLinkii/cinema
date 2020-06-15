package cinema.model.mapper;

import cinema.model.ShoppingCart;
import cinema.model.dto.response.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto toDto(ShoppingCart model) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setTickets(
                model.getTickets().stream()
                        .map(ticketMapper::toDto)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}