package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.criteria.TaskCriteriaRequest;
import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.request.TaskRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.dto.response.TaskResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    void createTask(TaskRequest taskRequest);

    List<TaskResponse> getAllTasks(Pageable pageable, TaskCriteriaRequest criteriaRequest);

    void updateTaskStatus(Long id, String status);

}