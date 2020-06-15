package cinema.model.mapper;

import cinema.model.Order;
import cinema.model.dto.response.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto toDto(Order model) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(model.getId());
        dto.setTickets(
                model.getTickets().stream()
                        .map(ticketMapper::toDto)
                        .collect(Collectors.toList())
        );
        dto.setOrderDate(model.getOrderDate());
        return dto;
    }
}
