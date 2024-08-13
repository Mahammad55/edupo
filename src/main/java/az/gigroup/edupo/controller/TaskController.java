package az.gigroup.edupo.controller;

import az.gigroup.edupo.dto.criteria.TaskCriteriaRequest;
import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.request.TaskRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.dto.response.TaskResponse;
import az.gigroup.edupo.service.CustomerService;
import az.gigroup.edupo.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/task")
@Tag(name = "Task Controller")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@Validated @RequestBody TaskRequest taskRequest) {
        taskService.createTask(taskRequest);
        return ResponseEntity.status(CREATED).body("Task successfully created!");
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAllTasks(@PageableDefault(sort = "dueDate") Pageable pageable, TaskCriteriaRequest criteriaRequest) {
        return ResponseEntity.ok(new PageImpl<>(taskService.getAllTasks(pageable, criteriaRequest)));
    }

    //
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
//        return ResponseEntity.ok(customerService.getCustomerById(id));
//    }
//
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Validated @RequestParam String taskStatus) {
        taskService.updateTaskStatus(id, taskStatus);
        return ResponseEntity.ok("TaskStatus updated successfully!!");
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
//        customerService.deleteCustomer(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<CustomerResponse>> getCustomerByName(@RequestParam String name) {
//        return ResponseEntity.ok(customerService.getCustomerByName(name));
//    }
}
