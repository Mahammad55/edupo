package az.gigroup.edupo.dto.request;

import az.gigroup.edupo.enums.GenderType;
import az.gigroup.edupo.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;

    private String email;

    private GenderType genderType;

    private String phone;

    private String password;

    private Roles role;
}
