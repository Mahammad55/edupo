package az.gigroup.edupo.dto.response;

import az.gigroup.edupo.enums.TaskStatus;
import az.gigroup.edupo.enums.TaskType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskResponse {

    private String note;
    private TaskType taskType;
    private TaskStatus status;
    private LocalDate dueDate;
    private Integer customerId;
    private String taskSubject;
    private String customerName;
}
//    note -> musterinin bezi suallari var idi kursla bagli;
//    taskType -> TO_DO_TASK
//    taskStatus-> OPEN, IN_PROGRESS, DONE;
//    dueDate -> 2024-09-09;
//    customerId -> 123 (isteye bagli olacaq);
//    taskSubject -> musterinin suallarini cavablamaq;
//    customerName -> Mezahir;