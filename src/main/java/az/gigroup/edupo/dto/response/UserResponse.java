package az.gigroup.edupo.dto.response;

import az.gigroup.edupo.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String name;

    private String email;

    private String jobPosition;

    private GenderType gender;

    private String phone;
}
