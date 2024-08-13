package az.gigroup.edupo.dto.criteria;

import az.gigroup.edupo.entity.User;
import az.gigroup.edupo.enums.TaskStatus;
import az.gigroup.edupo.enums.TaskType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskCriteriaRequest {

    private TaskType taskType;
    private String customerName;
    private boolean lastWeek;
    private boolean last15Days;
    private boolean lastMonth;
    private TaskStatus status;
}
