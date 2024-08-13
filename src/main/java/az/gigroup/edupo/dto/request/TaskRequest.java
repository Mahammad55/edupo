package az.gigroup.edupo.dto.request;

import az.gigroup.edupo.enums.TaskStatus;
import az.gigroup.edupo.enums.TaskType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskRequest {

    private String taskSubject;

    private String customerName;

    private Integer customerId;
    @NotNull(message = "cannot be blank")
    private LocalDate dueDate;
//    private Integer userId;
    private String note;
    private TaskStatus status;
}
