package az.gigroup.edupo.mapper;

import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.request.TaskRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.dto.response.TaskResponse;
import az.gigroup.edupo.entity.Course;
import az.gigroup.edupo.entity.Customer;
import az.gigroup.edupo.entity.Task;
import az.gigroup.edupo.enums.NextStep;
import az.gigroup.edupo.enums.Stages;
import az.gigroup.edupo.enums.TaskStatus;
import az.gigroup.edupo.enums.TaskType;
import org.mapstruct.*;

import static az.gigroup.edupo.enums.NextStep.FOLLOW_UP;
import static az.gigroup.edupo.enums.NextStep.RETARGET_LATER;

@Mapper(componentModel = "spring")
public interface TaskMapper {

//    @Mapping(target = "status", source = "taskStatus", qualifiedByName = "setTaskStatus")
    Task requestToEntity(TaskRequest taskRequest);

    @AfterMapping
    default void setDefaultTaskType(@MappingTarget Task task) {
        if (task.getTaskType() == null) {
            task.setTaskType(TaskType.TO_DO_TASK);
        }
    }
//    @Named("setTaskStatus")
//    default TaskStatus setTaskStatus(String taskStatus) {
//        if (taskStatus == null) {
//            return TaskStatus.OPEN;
//        }
//        return TaskStatus.valueOf(taskStatus);
//    }


    TaskResponse mapToResponse(Task task);

}
