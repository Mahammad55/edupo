package az.gigroup.edupo.dto.response;

import az.gigroup.edupo.enums.GenderType;
import az.gigroup.edupo.enums.NextStep;
import az.gigroup.edupo.enums.Stages;
import az.gigroup.edupo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;

    private String name;

    private Stages stages;

    private String mobileNumber;

    private String email;

    private GenderType genderType;

    private Status status;

    private List<CourseResponse> courses;

    private long probability;

    private NextStep nextStep;

    private double price;
}
