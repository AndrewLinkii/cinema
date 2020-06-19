package cinema.validation;

import cinema.model.dto.request.UserRequestDto;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator
        implements ConstraintValidator<PasswordsMatch, UserRequestDto> {

    public boolean isValid(UserRequestDto userRegistrationDto,
                           ConstraintValidatorContext context) {
        return Objects.equals(userRegistrationDto.getPassword(),
                userRegistrationDto.getRepeatPassword());
    }
}
