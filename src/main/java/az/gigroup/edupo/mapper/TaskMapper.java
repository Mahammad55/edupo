package az.gigroup.edupo.mapper;

import az.gigroup.edupo.dto.request.TaskRequest;
import az.gigroup.edupo.dto.response.TaskResponse;
import az.gigroup.edupo.entity.Task;
import az.gigroup.edupo.enums.TaskType;
import org.mapstruct.*;

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



    TaskResponse mapToResponse(Task task);

}
