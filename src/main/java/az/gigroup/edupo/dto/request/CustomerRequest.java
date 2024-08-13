package az.gigroup.edupo.dto.request;

import az.gigroup.edupo.annotation.ValidPhoneNumber;
import az.gigroup.edupo.enums.GenderType;
import az.gigroup.edupo.enums.Stage;
import az.gigroup.edupo.enums.Status;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;

    private Stage stage;

    @ValidPhoneNumber
    private String mobileNumber;

    @Email(message = "Email address must be valid")
    private String email;

    private GenderType gender;

    private Status status;

    private Long courseId;
}
