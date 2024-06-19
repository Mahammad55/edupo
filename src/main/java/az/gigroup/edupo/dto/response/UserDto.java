package az.gigroup.edupo.dto.response;

import az.gigroup.edupo.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private String email;
    private String password;
    private Roles role;
}
