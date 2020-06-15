package cinema.model.mapper;

import cinema.model.User;
import cinema.model.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toDto(User model) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(model.getId());
        dto.setEmail(model.getEmail());
        return dto;
    }
}
