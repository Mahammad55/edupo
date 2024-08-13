package az.gigroup.edupo.service.impl;

import az.gigroup.edupo.dto.criteria.TaskCriteriaRequest;
import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.request.TaskRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.dto.response.TaskResponse;
import az.gigroup.edupo.entity.Course;
import az.gigroup.edupo.entity.Customer;
import az.gigroup.edupo.entity.Task;
import az.gigroup.edupo.enums.TaskStatus;
import az.gigroup.edupo.exception.AlreadyExistsException;
import az.gigroup.edupo.exception.NotFoundException;
import az.gigroup.edupo.mapper.CustomerMapper;
import az.gigroup.edupo.mapper.TaskMapper;
import az.gigroup.edupo.repository.CourseRepository;
import az.gigroup.edupo.repository.CustomerRepository;
import az.gigroup.edupo.repository.TaskRepository;
import az.gigroup.edupo.service.CustomerService;
import az.gigroup.edupo.service.TaskService;
import az.gigroup.edupo.service.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.gigroup.edupo.enums.Active.ACTIVE;
import static az.gigroup.edupo.enums.Active.DEACTIVE;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public void createTask(TaskRequest taskRequest) {
        Task task = taskMapper.requestToEntity(taskRequest);
        taskRepository.save(task);
    }

    @Override
    public List<TaskResponse> getAllTasks(Pageable pageable, TaskCriteriaRequest criteriaRequest) {
        Specification<Task> taskByCriteria = TaskSpecification.getTaskByCriteria(criteriaRequest);
        return taskRepository.findAll(taskByCriteria, pageable).stream().
                map(taskMapper::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void updateTaskStatus(Long id, String status) {
        Task task = taskRepository.
                findById(id).orElseThrow(() -> new NotFoundException("TASK_NOT_FOUND"));
        task.setStatus(TaskStatus.valueOf(status));
        taskRepository.save(task);
    }
//


//    @Override
//    public CustomerResponse getCustomerById(Long id) {
//        return customerRepository.findByIdAndActive(id, ACTIVE.value)
//                .map(customerMapper::entityToResponse)
//                .orElseThrow(() -> new NotFoundException("Customer by id=%d not found".formatted(id)));
//    }
//
//    @Override
//    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
//        customerRepository.findByIdAndActive(id, ACTIVE.value)
//                .orElseThrow(() -> new NotFoundException("Customer by id=%d not found".formatted(id)));
//
//        Course course = courseRepository.findById(customerRequest.getCourseId())
//                .orElseThrow(() -> new NotFoundException("Course by id=%d not found".formatted(customerRequest.getCourseId())));
//
//        Customer customer = customerMapper.requestToEntity(customerRequest);
//        customer.setId(id);
//        customer.setCourse(course);
//        customer.setActive(ACTIVE.value);
//        return customerMapper.entityToResponse(customerRepository.save(customer));
//    }
//
//    @Override
//    public void deleteCustomer(Long id) {
//        Customer customer = customerRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Customer by id=%d not found".formatted(id)));
//        customer.setActive(DEACTIVE.value);
//    }
//
//    @Override
//    public List<CustomerResponse> getCustomerByName(String name) {
//        List<Customer> customerList = customerRepository.findByNameAndActive(name, ACTIVE.value);
//
//        if (customerList.isEmpty()) throw new NotFoundException("Customer by name=%s not found".formatted(name));
//
//        return customerList.stream().map(customerMapper::entityToResponse).toList();
//    }
}
