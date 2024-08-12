package az.gigroup.edupo.dto.request;

import az.gigroup.edupo.annotation.ValidPhoneNumber;
import az.gigroup.edupo.enums.GenderType;
import az.gigroup.edupo.enums.Roles;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;

    @Email(message = "Email address must be valid")
    private String email;

    private GenderType gender;

    @ValidPhoneNumber
    private String phone;

    private String password;

    private Roles role;
}
