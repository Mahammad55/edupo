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
        customerRepository.save(customer);
        return customerMapper.entityToResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream()
                .map(customerMapper::entityToResponse)
                .toList();
    }


//    public double calculateTotalPrice(Customer customer) {
//        return customer.getCourseList().stream()
//                .mapToDouble(course -> course.getPrice())
//                .sum();
//    }
//
//    public CustomerResponse createCustomerResponse(CustomerRequest customerRequest) {
//        Customer createdCustomer = createCustomer(customer);
//        double totalPrice = calculateTotalPrice(createdCustomer);
//        return new CustomerResponse(createdCustomer, totalPrice);
//    }
//    @Override
//    public Optional<Customer> getCustomerById(Long id) {
//        return Optional.ofNullable(customerRepository.findById(id)
//                .orElseThrow(() -> new CustomerNotFoundException(id)));
//    }
//
//    @Override
//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
//
//    @Override
//    public Customer updateCustomer(Long id, Customer updatedCustomer) {
//        return customerRepository.findById(id)
//                .map(customer -> {
//                    customer.setName(updatedCustomer.getName());
//                    customer.setEmail(updatedCustomer.getEmail());
//                    customer.setMobileNumber(updatedCustomer.getMobileNumber());
//                    return customerRepository.save(customer);
//                }).orElseThrow(() -> new CustomerNotFoundException(id));
//    }
//
//    @Override
//    public void deleteCustomer(Long id) {
//        if (!customerRepository.existsById(id)) {
//            throw new CustomerNotFoundException(id);
//        }
//        customerRepository.deleteById(id);
//    }
//
//    @Override
//    public List<Customer> findCustomersByName(String name) {
//        return customerRepository.findByName(name);
//    }
}
