package cinema.model.dto.request;

import cinema.validation.EmailConstraint;
import cinema.validation.PasswordsMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordsMatch(
        password = "password",
        repeatPassword = "repeatPassword"
)
public class UserRequestDto {
    @EmailConstraint
    private String email;
    @NotNull
    @Size(min = 5, message = "Password should contains 5 or more symbols")
    private String password;
    @NotNull
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
