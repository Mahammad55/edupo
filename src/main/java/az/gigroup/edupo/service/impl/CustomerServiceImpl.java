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

import static az.gigroup.edupo.enums.Active.ACTIVE;
import static az.gigroup.edupo.enums.Active.DEACTIVE;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final CourseRepository courseRepository;

    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        if (customerRepository.existsCustomerByEmailAndActive(customerRequest.getEmail(), ACTIVE.value)) {
            throw new AlreadyExistsException("Customer by email=%s already exist".formatted(customerRequest.getEmail()));
        }

        Course course = courseRepository.findById(customerRequest.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course by id=%d not found".formatted(customerRequest.getCourseId())));

        Customer customer = customerMapper.requestToEntity(customerRequest);
        customer.setCourse(course);
        return customerMapper.entityToResponse(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAllByActive(ACTIVE.value).stream()
                .map(customerMapper::entityToResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findByIdAndActive(id, ACTIVE.value)
                .map(customerMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException("Customer by id=%d not found".formatted(id)));
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        customerRepository.findByIdAndActive(id, ACTIVE.value)
                .orElseThrow(() -> new NotFoundException("Customer by id=%d not found".formatted(id)));

        Course course = courseRepository.findById(customerRequest.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course by id=%d not found".formatted(customerRequest.getCourseId())));

        Customer customer = customerMapper.requestToEntity(customerRequest);
        customer.setId(id);
        customer.setCourse(course);
        customer.setActive(ACTIVE.value);
        return customerMapper.entityToResponse(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findByIdAndActive(id, ACTIVE.value)
                .orElseThrow(() -> new NotFoundException("Customer by id=%d not found".formatted(id)));
        customer.setActive(DEACTIVE.value);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerResponse> getCustomerByName(String name) {
        List<Customer> customerList = customerRepository.findByNameAndActive(name, ACTIVE.value);

        if (customerList.isEmpty()) throw new NotFoundException("Customer by name=%s not found".formatted(name));

        return customerList.stream().map(customerMapper::entityToResponse).toList();
    }
}
