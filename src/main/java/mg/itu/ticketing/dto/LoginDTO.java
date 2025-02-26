package mg.itu.ticketing.dto;

import lombok.Getter;
import lombok.Setter;
import mg.itu.prom16.winter.validation.annotation.Email;

@Getter
@Setter
public class LoginDTO {
    @Email
    private String email;
    private String password;
}
