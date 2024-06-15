package az.gigroup.edupo.service.impl;

import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.entity.Customer;
import az.gigroup.edupo.exception.CustomerAlreadyExistsException;
import az.gigroup.edupo.exception.CustomerNotFoundException;
import az.gigroup.edupo.repository.CustomerRepository;
import az.gigroup.edupo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    public Customer createCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new CustomerAlreadyExistsException(customer.getEmail());
        }
        return customerRepository.save(customer);
    }

    public double calculateTotalPrice(Customer customer) {
        return customer.getCourseList().stream()
                .mapToDouble(course -> course.getPrice())
                .sum();
    }

    public CustomerResponse createCustomerResponse(Customer customer) {
        Customer createdCustomer = createCustomer(customer);
        double totalPrice = calculateTotalPrice(createdCustomer);
        return new CustomerResponse(createdCustomer, totalPrice);
    }
    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return Optional.ofNullable(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updatedCustomer.getName());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setMobileNumber(updatedCustomer.getMobileNumber());
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        return customerRepository.findByName(name);
    }
}
