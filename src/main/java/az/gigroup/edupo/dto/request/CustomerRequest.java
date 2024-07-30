package az.gigroup.edupo.dto.request;

import az.gigroup.edupo.enums.GenderType;
import az.gigroup.edupo.enums.Stages;
import az.gigroup.edupo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;

    private Stages stages;

    private String mobileNumber;

    private String email;

    private GenderType genderType;

    private Status status;

    private Long courseId;
}
