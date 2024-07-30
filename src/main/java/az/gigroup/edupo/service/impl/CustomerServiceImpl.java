package az.gigroup.edupo.service.impl;

import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.entity.Course;
import az.gigroup.edupo.entity.Customer;
import az.gigroup.edupo.exception.AlreadyExistsException;
import az.gigroup.edupo.exception.NotFoundException;
import az.gigroup.edupo.mapper.CustomerMapper;
import az.gigroup.edupo.repository.CourseRepository;
import az.gigroup.edupo.repository.CustomerRepository;
import az.gigroup.edupo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final CourseRepository courseRepository;

    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        if (customerRepository.existsCustomerByEmail(customerRequest.getEmail())) {
            throw new AlreadyExistsException("Customer by email=%s already exist".formatted(customerRequest.getEmail()));
        }

        Course course = courseRepository.findById(customerRequest.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course by id=%d not found".formatted(customerRequest.getCourseId())));

        Customer customer = customerMapper.requestToEntity(customerRequest);
        customer.setCourses(List.of(course));
        return customerMapper.entityToResponse(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream()
                .map(customerMapper::entityToResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException("Customer by id=%d not found".formatted(id)));
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer by id=%d not found".formatted(id)));

        Course course = courseRepository.findById(customerRequest.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course by id=%d not found".formatted(customerRequest.getCourseId())));

        Customer customer = customerMapper.requestToEntity(customerRequest);
        customer.setId(id);
        customer.setCourses(List.of(course));
        return customerMapper.entityToResponse(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer by id=%d not found".formatted(id)));

        customerRepository.deleteById(id);
    }
}
