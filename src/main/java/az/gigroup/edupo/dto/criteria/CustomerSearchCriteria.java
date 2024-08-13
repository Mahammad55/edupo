package az.gigroup.edupo.dto.criteria;

import az.gigroup.edupo.enums.Stage;
import lombok.Data;

@Data
public class CustomerSearchCriteria {
    private String name;

    private Stage stage;
}
